package com.kalashnyk.denys.defaultproject.presentation.activities.location

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.LocationChooserDataBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.presentation.adapter.LocationAdapter
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity
import com.kalashnyk.denys.moduleproject.utils.BUNDLE_LOCATION_LAT
import com.kalashnyk.denys.moduleproject.utils.BUNDLE_LOCATION_LNG
import com.kalashnyk.denys.moduleproject.utils.LOCATION_REQUEST_INTERVAL
import com.kalashnyk.denys.defaultproject.utils.extention.gone
import com.kalashnyk.denys.defaultproject.utils.extention.hideKeyboard
import com.kalashnyk.denys.defaultproject.utils.extention.toLatLng
import com.kalashnyk.denys.defaultproject.utils.extention.visible
import com.kalashnyk.denys.defaultproject.utils.logger.Logger
import com.kalashnyk.denys.defaultproject.utils.permission.PermissionHelper.checkLocationPermission
import io.reactivex.functions.Consumer

class LocationChooserActivity : BaseActivity<LocationChooserDataBinding>(),
    LocationChooserSearchCallback,
    LocationChooserPlaceCallback,
    OnMapReadyCallback,
    GoogleApiClient.ConnectionCallbacks {

    private lateinit var locationManager: LocationManager
    private lateinit var locationRequest: LocationRequest
    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var googleMap: GoogleMap

    private lateinit var currentMarker: Marker

    private var isLocationInitialized: Boolean = false
    private var isLocationEnabled: Boolean = false

    private var autoCompleteTextWatcher: LocationChooserTextWatcher? = null

    private var location: LatLng? = null

    override fun getLayoutId(): Int = R.layout.activity_location_chooser

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun setupViewLogic(binder: LocationChooserDataBinding) {
        setupToolbar()
        setupMapView()
        setupViews()
        getCurrentLocation()
    }

    override fun onMapReady(p0: GoogleMap?) {
        p0?.let {
            googleMap = it.apply {
                setOnMyLocationButtonClickListener {
                    try {
                        showSendDialog(it.myLocation.toLatLng())
                    } catch (e: Throwable) {
                        Logger.e(this::class, e)
                    }
                    return@setOnMyLocationButtonClickListener false
                }
                setOnMapClickListener { location ->
                    showSendDialog(location)
                }
            }
        }
        checkLocationPermission(this,
            Consumer { isGranted: Boolean? ->
                if (isGranted!!) {
                    setupGoogleApiClient()
                    googleMap.isMyLocationEnabled = true
                }
            }
        )
    }

    override fun onConnected(p0: Bundle?) {
        locationRequest = LocationRequest().apply {
            interval = LOCATION_REQUEST_INTERVAL
            fastestInterval = LOCATION_REQUEST_INTERVAL
            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        }

        if (!isLocationEnabled) {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient,
                locationRequest
            ) { location ->
                if (!isLocationInitialized) {
                    showSendDialog(location.toLatLng())
                    isLocationInitialized = true
                }
            }
        }
    }

    override fun onConnectionSuspended(p0: Int) {
        Logger.d(this::class, "onConnectionSuspended", p0.toString())
    }

    override fun searchAddresses(addresses: List<Address>) {
        Logger.d(this::class, "searchAddresses", addresses.toString())

        val tempList: ArrayList<LocationModel> = arrayListOf()
        tempList.clear()

        if (!addresses.isNullOrEmpty()) {
            addresses.forEach {
                tempList.add(LocationModel(it, true))
            }
            tempList.last()?.decorate = false

            viewBinding.rvAutoComplete.apply {
                adapter = null
                showAutoComplete()
                layoutManager = LinearLayoutManager(this@LocationChooserActivity)
                adapter = LocationAdapter(
                    this@LocationChooserActivity,
                    tempList,
                    this@LocationChooserActivity
                )
            }
        } else viewBinding.rvAutoComplete.apply {
            hideAutoComplete()
        }
    }

    override fun onAddressClicked(address: Address) {
        showSendDialog(LatLng(address.latitude, address.longitude), address.getAddressLine(0))
        viewBinding.searchView.removeTextChangedListener(autoCompleteTextWatcher)
        viewBinding.searchView.setText(address.getAddressLine(0).toString())
        Handler().postDelayed({setupSearchView()}, 1000L)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    private fun getCurrentLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        isLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun setupGoogleApiClient() {
        googleApiClient = GoogleApiClient.Builder(this.baseContext)
            .addConnectionCallbacks(this)
            .addApi(LocationServices.API)
            .build()
            .apply { connect() }
    }

    private fun setupMapView() =
        (supportFragmentManager.findFragmentById(R.id.fragmentGoogleMap) as SupportMapFragment?)?.apply {
            getMapAsync(this@LocationChooserActivity)
        }

    private fun setupToolbar() = supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(true)
        title=getString(R.string.title_geo)
    }

    private fun setupViews() = viewBinding.apply {
        sendGeoDialog.apply {
            clipToOutline = true
            setOnClickListener {
                val locationResult = Intent()
                this@LocationChooserActivity.location.let {
                    if (it == null) {
                        setResult(Activity.RESULT_CANCELED)
                    } else {
                        locationResult.apply {
                            putExtra(BUNDLE_LOCATION_LAT, it.latitude)
                            putExtra(BUNDLE_LOCATION_LNG, it.longitude)
                        }
                        setResult(Activity.RESULT_OK, locationResult)
                    }
                    finish()
                }
            }
        }
        btnTextClear.setOnClickListener {
            setupSearchView(true)
            searchAddresses(arrayListOf())
        }
        setupSearchView()
    }

    private fun showSendDialog(location: LatLng, address: String? = null) {
        googleMap.clear()
        this.location = location
        viewBinding.sendGeoDialog.visible()
        viewBinding.tvAddress.text = address ?: getAddress(location.latitude, location.longitude)
        hideAutoComplete()
        hideKeyboard()
        showLocationPin(location.latitude, location.longitude)
    }

    private fun showLocationPin(lat: Double, lng: Double) {
        viewBinding.searchView.clearFocus()
        viewBinding.sendGeoDialog.requestFocus()
        currentMarker = googleMap.addMarker(MarkerOptions().apply {
            position(LatLng(lat, lng))
            title(getString(R.string.title_geo_current))
            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        })

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lng), 11F))
    }

    private fun showAutoComplete() = viewBinding.rvAutoComplete.apply {
        visible()
        z = 11f
    }

    private fun hideAutoComplete() = viewBinding.rvAutoComplete.apply {
        z = 0f
        gone()
    }

    private fun setupSearchView(needToClear: Boolean = false) {
        autoCompleteTextWatcher = LocationChooserTextWatcher(
            this,
            viewBinding.searchView,
            viewBinding.btnTextClear,
            this
        )
        viewBinding.searchView.apply {
            if (needToClear) setText("")
            addTextChangedListener(autoCompleteTextWatcher)
            setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    v.clearFocus()
                    viewBinding.sendGeoDialog.requestFocus()
                }
            }
        }
    }

    private fun getAddress(lat: Double, lng: Double): String {
        val addresses: List<Address> = Geocoder(this)
            .getFromLocation(lat, lng, 1)

        /*
        // Other data that can be got from GeoCoder
        // Uncomment if you will need this

        val city = addresses[0].locality
        val state = addresses[0].adminArea
        val country = addresses[0].countryName
        val postalCode = addresses[0].postalCode
        val knownName = addresses[0].featureName
         */

        return addresses[0].getAddressLine(0)
    }
}

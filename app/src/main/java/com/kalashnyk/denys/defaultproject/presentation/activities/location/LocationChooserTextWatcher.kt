package com.kalashnyk.denys.defaultproject.presentation.activities.location

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.kalashnyk.denys.defaultproject.utils.extention.gone
import com.kalashnyk.denys.defaultproject.utils.extention.visible
import com.kalashnyk.denys.defaultproject.utils.logger.Logger
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class LocationChooserTextWatcher(
    private val context: Context,
    private val searchView: EditText,
    private val clearView: View,
    private val callback: LocationChooserSearchCallback
) : TextWatcher {

    private val compositeDisposable = CompositeDisposable()

    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        searchView.text.toString().trim().apply {
            processLocationSearch(this)
            changeClearViewState(this)
        }
    }

    private fun processLocationSearch(query: String) = compositeDisposable.add(
        Single.just(query)
            .delay(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map {
                val geoCoder = Geocoder(context)
                var addresses: List<Address> = arrayListOf()

                addresses = geoCoder.getFromLocationName(query, 5)
                if (addresses != null && !addresses.equals("")) {
                    return@map addresses
                } else {
                    return@map arrayListOf<Address>()
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.searchAddresses(it)
            }, {
                Logger.e(this::class, it)
            })
    )

    private fun changeClearViewState(query: String?) = clearView.apply {
        if (query.isNullOrEmpty()) gone() else visible()
    }
}
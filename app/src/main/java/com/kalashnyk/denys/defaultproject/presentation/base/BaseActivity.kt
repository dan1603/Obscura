package com.kalashnyk.denys.defaultproject.presentation.base

import android.Manifest
import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import com.kalashnyk.denys.defaultproject.App
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.utils.extention.hideKeyboardEx
import java.util.ArrayList

abstract class BaseActivity : AppCompatActivity() {
    protected open val PERMISSION_REQUEST = 5

    open var arrayPermission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    open lateinit var messageRationalePermission: String
    open lateinit var messageNecessaryPermissions: String
    protected var requestCode: Int? = null
    private var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDaggerDependencies()
    }

    protected fun initializeToolbar(toolbar: Toolbar) {
        mToolbar = toolbar
        mToolbar?.apply {
            setNavigationOnClickListener({ onBackPressed() })
            setActionBar(this)
            actionBar?.title = ""
        }
    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down)
    }

    protected fun getToolbar(): Toolbar? = mToolbar

    protected fun setToolbarTitle(title: CharSequence) {
        mToolbar?.title = title
    }

    private fun findViewAt(viewGroup: ViewGroup, x: Int, y: Int): View? {
        (0 until viewGroup.childCount)
            .map { viewGroup.getChildAt(it) }
            .forEach {
                when (it) {
                    is ViewGroup -> {
                        val foundView = findViewAt(it, x, y)
                        if (foundView?.isShown!!) return foundView
                    }
                    else -> {
                        val location = IntArray(2)
                        it.getLocationOnScreen(location)
                        val rect = Rect(location[0], location[1], location[0] + it.width, location[1] + it.height)
                        if (rect.contains(x, y)) return it
                    }
                }
            }
        return null
    }

    private fun hideKeyboard() = this.hideKeyboardEx()

    @TargetApi(Build.VERSION_CODES.M)
    fun isStoragePermissionGranted(requestCode: Int = 0): Boolean {
        this.requestCode = requestCode
        if (checkPermissionList()) {
            return true
        } else {
            requestPermission()
            return false
        }
    }

    fun checkPermissionList(): Boolean {
        val list = ArrayList<Boolean>()
        arrayPermission.forEach {
            list.add(ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED)
        }
        return list.all { it }
    }

    open fun openNeededАction(requestCodeIntent: Int) {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, requestCodeIntent)
    }

    fun checkPermissionRationaleList(): Boolean {
        val list = ArrayList<Boolean>()
        arrayPermission.forEach {
            list.add(ActivityCompat.shouldShowRequestPermissionRationale(this, it))
        }
        return list.all { it }
    }

    fun showNoGalleryPermission() {
        openApplicationSettings()
        Toast.makeText(this, messageNecessaryPermissions, Toast.LENGTH_LONG).show()
    }

    fun openApplicationSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:$packageName"))
        startActivityForResult(appSettingsIntent, PERMISSION_REQUEST)
    }

    fun requestPermission() {
        ActivityCompat.requestPermissions(this,
            arrayPermission,
            PERMISSION_REQUEST)
    }

    fun replaceFragment(resLayout : Int, fragment : BaseFragment) {

    }

    protected abstract fun injectDependency(component: ViewModelComponent)

    private fun createDaggerDependencies() {
        injectDependency((application as App).getViewModelComponent())
    }
}
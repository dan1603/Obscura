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
import androidx.core.app.ActivityCompat

import androidx.core.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kalashnyk.denys.defaultproject.App
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.utils.extention.hideKeyboard
import java.util.ArrayList

abstract class BaseActivity : AppCompatActivity() {
    protected open val PERMISSION_REQUEST = 5

    open var arrayPermission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    open lateinit var messageNecessaryPermissions: String
    protected var requestCode: Int? = null
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDaggerDependencies()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected fun initializeToolbar(toolbar: Toolbar) {
        this.toolbar = toolbar
        this.toolbar?.apply {
            setNavigationOnClickListener { onBackPressed() }
            setActionBar(this)
            actionBar?.title = ""
        }
    }

    @RequiresApi(Build.VERSION_CODES.ECLAIR)
    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
    }

    @RequiresApi(Build.VERSION_CODES.ECLAIR)
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down)
    }

    protected fun getToolbar(): Toolbar? = this.toolbar

    protected fun setToolbarTitle(title: CharSequence) {
        toolbar?.title = title
    }

    private fun findViewAt(viewGroup: ViewGroup, x: Int, y: Int): View? {
        (0 until viewGroup.childCount)
            .map { viewGroup.getChildAt(it) }
            .forEach {
                when (it) {
                    is ViewGroup -> {
                        val foundView = findViewAt(it, x, y)
                        if (foundView?.isShown ?: return@forEach) return foundView
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

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStackImmediate()
            hideKeyboard()
            return
        }
        super.onBackPressed()
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun isPermissionGranted(arrayPermission: Array<String>, requestCode: Int = 0): Boolean {
        if (checkPermissionList(arrayPermission)) {
            return true
        } else {
            requestPermission(arrayPermission, requestCode)
            return false
        }
    }

    private fun checkPermissionList(arrayPermission: Array<String>): Boolean {
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

    fun checkPermissionRationaleList(arrayPermission: Array<String>): Boolean {
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

    private fun openApplicationSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:$packageName")
        )
        startActivityForResult(appSettingsIntent, PERMISSION_REQUEST)
    }

    fun requestPermission(arrayPermission : Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(this, arrayPermission, requestCode)
    }

    fun replaceFragment(container: Int, fragment: Fragment) {
        replaceFragment(container, fragment, false, false)
    }

    fun replaceFragment(container: Int, fragment: Fragment, addToBackStack: Boolean, moveOnRight: Boolean) {
        replaceFragment(container, fragment, addToBackStack, true, moveOnRight)
    }

    private fun replaceFragment(
        container: Int,
        fragment: Fragment,
        addToBackStack: Boolean,
        needAnimate: Boolean,
        moveOnRight: Boolean
    ) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentTag = fragment.javaClass.simpleName
        if (addToBackStack) fragmentTransaction = fragmentTransaction.addToBackStack(fragmentTag)
        if (needAnimate) {
            val enterAnimation = if (moveOnRight) R.animator.slide_in_left else R.animator.pop_out_right
            val exitAnimation = if (moveOnRight) R.animator.slide_out_right else R.animator.pop_in_left
            val popEnterAnimation = if (moveOnRight) R.animator.pop_out_right else R.animator.slide_in_left
            val popExitAnimation = if (moveOnRight) R.animator.pop_in_left else R.animator.slide_out_right
            fragmentTransaction.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        }
        fragmentTransaction.replace(container, fragment, fragmentTag).commit()
    }

    protected abstract fun injectDependency(component: ViewModelComponent)

    private fun createDaggerDependencies() {
        injectDependency((application as App).getViewModelComponent())
    }
}
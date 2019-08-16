package com.kalashnyk.denys.defaultproject.presentation.base

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kalashnyk.denys.defaultproject.App
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.presentation.navigation.INavigation
import com.kalashnyk.denys.defaultproject.presentation.navigation.NavigationImpl
import com.kalashnyk.denys.defaultproject.utils.extention.hideKeyboard
import com.kalashnyk.denys.defaultproject.utils.extention.initializeToolbar
import com.kalashnyk.denys.defaultproject.utils.permission.IPermissionManager
import com.kalashnyk.denys.defaultproject.utils.permission.PermissionManagerImpl


/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    /**
     *
     */
    protected lateinit var viewBinding: V

    /**
     *
     */
    protected lateinit var navigator : INavigation

    /**
     *
     */
    protected lateinit var permissionManager : IPermissionManager

    /**
     *
     */
    open lateinit var messageNecessaryPermissions: String

    private var toolbar: Toolbar? = null


    /**
     * @param component
     */
    abstract fun injectDependency(component: ViewModelComponent)

    /**
     * @return
     */
    abstract fun getLayoutId(): Int

    /**
     * @param binder
     */
    abstract fun setupViewLogic(binder : V)

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, getLayoutId())
        navigator = NavigationImpl(this)
        permissionManager = PermissionManagerImpl()
        createDaggerDependencies()
        setupViewLogic(viewBinding)
    }

    /**
     *
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected fun initializeToolbar(toolbar: Toolbar) {
        this.toolbar = toolbar.initializeToolbar(actionBar, this)
    }

    /**
     *
     */
    protected fun getToolbar(): Toolbar? = this.toolbar

    /**
     *
     */
    @RequiresApi(Build.VERSION_CODES.ECLAIR)
    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
    }

    /**
     *
     */
    @RequiresApi(Build.VERSION_CODES.ECLAIR)
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down)
    }

    /**
     * handle back stack with handling state of children of activity
     */
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount >= 1) {
            supportFragmentManager.popBackStackImmediate()
            hideKeyboard()
            return
        }
        super.onBackPressed()
    }

    /**
     * check permissions and handel them state
     */
    @TargetApi(Build.VERSION_CODES.M)
    fun isPermissionGranted(arrayPermission: Array<String>, requestCode: Int = 0): Boolean =
        if (this.permissionManager.hasPermission(this, arrayPermission)) {
            true
        } else {
            this.permissionManager.requestPermission(this, requestCode, arrayPermission)
            false
        }

    /**
     * for navigate to android settings
     */
    fun showNoGalleryPermission() {
        navigator.openSettings("package:$packageName")
        Toast.makeText(this, messageNecessaryPermissions, Toast.LENGTH_LONG).show()
    }

    private fun createDaggerDependencies() {
        injectDependency((application as App).getViewModelComponent())
    }
}

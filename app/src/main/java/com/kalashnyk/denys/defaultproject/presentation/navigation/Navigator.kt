package com.kalashnyk.denys.defaultproject.presentation.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.AuthActivity
import com.kalashnyk.denys.defaultproject.presentation.activities.main.MainActivity
import com.kalashnyk.denys.defaultproject.presentation.activities.splash.SplashActivity
import com.kalashnyk.denys.defaultproject.presentation.activities.welcome.WelcomeActivity
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.FragmentNavigator
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.FragmentNavigatorImpl
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.PageNavigationItem
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.Pages
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.TransitionBundle
import com.kalashnyk.denys.defaultproject.utils.ApplicationConstants

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 * navigation source for opening activities screens
 */
interface Navigation : FragmentNavigator {

    var navigatorSource: BaseActivity<*>
    fun openSplashScreen(context: Context)
    fun openWelcomeScreen(context: Context)
    fun openAuthScreen(typeScreen: Pages, flag : Int)
    fun openMainScreen()
    fun openDetailScreen(id: Int)
    fun openSettings(uriSetting : String)
    fun openGallery()
}

/**
 *
 */
class NavigationImpl(override var navigatorSource: BaseActivity<*>) : Navigation {

    /**
     *
     */
    private val fragmentNavigator: FragmentNavigator

    init {
        fragmentNavigator =
            FragmentNavigatorImpl(
                navigatorSource.supportFragmentManager
            )
    }
    /**
     *
     */
    override fun openGallery() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        navigatorSource.startActivityForResult(galleryIntent, ApplicationConstants.GALLERY_REQUEST)
    }

    /**
     * @param uriSetting - package:$packageName
     */
    override fun openSettings(uriSetting : String) {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse(uriSetting)
        )
        navigatorSource.startActivityForResult(appSettingsIntent, ApplicationConstants.SETTINGS_REQUEST)
    }

    /**
     *
     */
    override fun openSplashScreen(context: Context) {
        val intent = Intent(navigatorSource, SplashActivity::class.java).apply {
            this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        navigatorSource.startActivity(intent)
    }

    /**
     *
     */
    override fun openWelcomeScreen(context: Context) {
        val intent = Intent(navigatorSource, WelcomeActivity::class.java).apply {
            this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        navigatorSource.startActivity(intent)
    }

    /**
     *
     */
    override fun openAuthScreen(typeScreen: Pages, flag : Int) {
        val intent = Intent(navigatorSource, AuthActivity::class.java).apply {
            this.putExtra(ApplicationConstants.AUTH_TYPE_SCREEN, typeScreen)
            this.flags = flag
        }
        navigatorSource.startActivity(intent)
    }

    /**
     *
     */
    override fun openMainScreen() {
        val intent = Intent(navigatorSource, MainActivity::class.java).apply {
            this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        navigatorSource.startActivity(intent)
    }

    /**
     *
     */
    override fun openDetailScreen(id: Int) {
        val intent = Intent(navigatorSource, AuthActivity::class.java).apply {
            this.putExtra(ApplicationConstants.DETAIL_ID, id)
        }
        navigatorSource.startActivity(intent)
    }

    override fun goToPage(page: PageNavigationItem) {
        fragmentNavigator.goToPage(page)
    }

    override fun goToPage(page: PageNavigationItem, transitionBundle: TransitionBundle) {
        fragmentNavigator.goToPage(page, transitionBundle)
    }

    override fun goToPageForResult(page: PageNavigationItem, transitionBundle: TransitionBundle) {
        fragmentNavigator.goToPageForResult(page, transitionBundle)
    }

    override fun back(): Boolean {
        return fragmentNavigator.back()
    }

    override fun reset() {
        fragmentNavigator.reset()
    }
}

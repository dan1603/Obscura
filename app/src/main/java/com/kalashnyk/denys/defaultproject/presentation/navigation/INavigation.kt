package com.kalashnyk.denys.defaultproject.presentation.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.AuthActivity
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.IAuthFlow
import com.kalashnyk.denys.defaultproject.presentation.activities.main.MainActivity
import com.kalashnyk.denys.defaultproject.presentation.activities.splash.SplashActivity
import com.kalashnyk.denys.defaultproject.presentation.activities.welcome.WelcomeActivity
import com.kalashnyk.denys.defaultproject.utils.ApplicationConstants

/**
 * navigation source for opening screens
 */
interface INavigation {

    var navigatorSource: Activity
    fun openSplashScreen(context: Context)
    fun openWelcomeScreen(context: Context)
    fun openAuthScreen(typeScreen: IAuthFlow.NavigationType, flag : Int)
    fun openMainScreen()
    fun openDetailScreen(id: Int)
}

class NavigationImpl(override var navigatorSource: Activity) : INavigation {

    override fun openSplashScreen(context: Context) {
        val intent = Intent(navigatorSource, SplashActivity::class.java).apply {
            this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        navigatorSource.startActivity(intent)
    }

    override fun openWelcomeScreen(context: Context) {
        val intent = Intent(navigatorSource, WelcomeActivity::class.java).apply {
            this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        navigatorSource.startActivity(intent)
    }

    override fun openAuthScreen(typeScreen: IAuthFlow.NavigationType, flag : Int) {
        val intent = Intent(navigatorSource, AuthActivity::class.java).apply {
            this.putExtra(ApplicationConstants.AUTH_TYPE_SCREEN, typeScreen)
            this.flags = flag
        }
        navigatorSource.startActivity(intent)
    }

    override fun openMainScreen() {
        val intent = Intent(navigatorSource, MainActivity::class.java).apply {
            this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        navigatorSource.startActivity(intent)
    }

    override fun openDetailScreen(id: Int) {
        val intent = Intent(navigatorSource, AuthActivity::class.java).apply {
            this.putExtra(ApplicationConstants.DETAIL_ID, id)
        }
        navigatorSource.startActivity(intent)
    }
}

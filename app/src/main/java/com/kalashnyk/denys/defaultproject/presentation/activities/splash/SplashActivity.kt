package com.kalashnyk.denys.defaultproject.presentation.activities.splash

import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.ActivitySplashBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity
import com.kalashnyk.denys.defaultproject.utils.ApplicationConstants

/**
 *
 */
class SplashActivity : BaseActivity() {

    override fun injectDependency(component: ViewModelComponent) {
       component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
        Handler().postDelayed({
            navigator.openWelcomeScreen(this)
            finish()
        }, ApplicationConstants.splashTimer)
    }
}

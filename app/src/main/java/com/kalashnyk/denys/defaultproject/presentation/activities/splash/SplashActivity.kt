package com.kalashnyk.denys.defaultproject.presentation.activities.splash

import android.os.Handler
import com.kalashnyk.denys.defaultproject.App
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.SplashBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity
import com.kalashnyk.denys.moduleproject.utils.DELAY_3000

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class SplashActivity : BaseActivity<SplashBinding>() {

    /**
     * @param
     */
    override fun injectDependency(component: ViewModelComponent) {
       component.inject(this)
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int = R.layout.activity_splash

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: SplashBinding) {
        (application as App).saveMockToDatabase()
        Handler().postDelayed({
            navigator.openWelcomeScreen(this)
            finish()
        }, DELAY_3000)
    }
}

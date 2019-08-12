package com.kalashnyk.denys.defaultproject.presentation.activities.welcome

import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.WelcomeDataBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class WelcomeActivity :  BaseActivity<WelcomeDataBinding>() {

    /**
     * @param component
     */
    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int = R.layout.activity_welcome

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: WelcomeDataBinding) {
        binding.navigator = navigator
    }
}

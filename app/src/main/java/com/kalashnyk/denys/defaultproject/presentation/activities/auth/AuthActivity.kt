package com.kalashnyk.denys.defaultproject.presentation.activities.auth

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.AuthDataBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.domain.AuthViewModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlow
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.PageNavigationItem
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.Pages
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.TransitionAnimation
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.TransitionBundle
import com.kalashnyk.denys.defaultproject.utils.AUTH_TYPE_SCREEN
import com.kalashnyk.denys.defaultproject.utils.extention.hideKeyboard

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class AuthActivity : BaseActivity<AuthDataBinding>(), AuthFlow.AuthListener {

    private lateinit var viewModel: AuthViewModel
    /**
     * @param component
     */
    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(AuthViewModel::class.java)
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int=R.layout.activity_auth

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: AuthDataBinding) {
        handlePage()
        showRootChild()
    }

    /**
     * @param flowModel
     * @param callback
     */
    override fun authRequest(flowModel: AuthFlowModel, callback: AuthFlow.AuthCallback) {
        viewModel.authRequest(flowModel, callback)
    }

    /**
     * @param type
     * @param callback
     */
    override fun socialAuth(type: AuthFlow.SocialAuthType, callback: AuthFlow.AuthCallback?) {
        when (type) {
            AuthFlow.SocialAuthType.FACEBOOK -> {
                //test
                navigator.openMainScreen()
            }
            AuthFlow.SocialAuthType.GOOGLE -> {
                //test
                navigator.openMainScreen()
            }
        }
    }

    /**
     *
     */
    override fun openScreen(page: Pages) {
        goToPage(PageNavigationItem(page), TransitionBundle(TransitionAnimation.ENTER_FROM_RIGHT))
    }

    /**
     *
     */
    override fun routBack() {
        super.onBackPressed()
    }

    private fun showRootChild() {
        openScreen(handlePage())
        hideKeyboard()
    }

    private fun handlePage() = intent?.extras?.getSerializable(AUTH_TYPE_SCREEN) as Pages
}

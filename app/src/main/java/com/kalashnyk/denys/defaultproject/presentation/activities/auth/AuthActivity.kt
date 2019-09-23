package com.kalashnyk.denys.defaultproject.presentation.activities.auth

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.AuthDataBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.domain.AuthViewModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.IAuthFlow
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.PageNavigationItem
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.Pages
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.TransitionAnimation
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.TransitionBundle
import com.kalashnyk.denys.defaultproject.utils.ApplicationConstants
import com.kalashnyk.denys.defaultproject.utils.extention.hideKeyboard

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class AuthActivity : BaseActivity<AuthDataBinding>(), IAuthFlow.IAuthListener {

    private lateinit var rootChildType: Pages
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
        handleIntent()
    }

    /**
     * @param flowModel
     * @param callback
     */
    override fun authRequest(flowModel: AuthFlowModel, callback: IAuthFlow.IAuthCallback) {
        viewModel.authRequest(flowModel, callback)
    }

    /**
     * @param type
     * @param callback
     */
    override fun socialAuth(type: IAuthFlow.SocialAuthType, callback: IAuthFlow.IAuthCallback?) {
        when (type) {
            IAuthFlow.SocialAuthType.FACEBOOK -> {
                //test
                navigator.openMainScreen()
            }
            IAuthFlow.SocialAuthType.GOOGLE -> {
                //test
                navigator.openMainScreen()
            }
        }
    }

    /**
     *
     */
    override fun openScreen(page: Pages) {

        val transitionBundle=
            if (page != rootChildType) TransitionBundle(TransitionAnimation.SLIDE_IN_FROM_RIGHT)
            else TransitionBundle(TransitionAnimation.NONE)

        goToPage(PageNavigationItem(page), transitionBundle)
    }

    /**
     *
     */
    override fun routBack() {
        super.onBackPressed()
    }

    private fun handleIntent() {
        rootChildType=intent?.extras?.getSerializable(ApplicationConstants.AUTH_TYPE_SCREEN) as Pages
        openScreen(rootChildType)
        hideKeyboard()
    }
}

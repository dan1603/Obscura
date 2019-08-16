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
import com.kalashnyk.denys.defaultproject.presentation.fragments.recover_account.RecoverAccountFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.sign_in.SignInFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.sign_up.SignUpFragment
import com.kalashnyk.denys.defaultproject.utils.ApplicationConstants
import com.kalashnyk.denys.defaultproject.utils.extention.hideKeyboard
import com.kalashnyk.denys.defaultproject.utils.extention.replaceFragment

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class AuthActivity : BaseActivity<AuthDataBinding>(), IAuthFlow.IAuthListener {

    private lateinit var rootChildType: IAuthFlow.NavigationType
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
    override fun openScreen(typeScreen: IAuthFlow.NavigationType) {

        /**
         * if typeScreen equal with rootChildType -
         * we don't use animation between rout fragments
         */
        val isRootChild=rootChildType != typeScreen

        when (typeScreen) {
            IAuthFlow.NavigationType.SIGN_IN_SCREEN -> {
                replaceFragment(R.id.auth_container, SignInFragment.newInstance(), isRootChild, isRootChild)
            }
            IAuthFlow.NavigationType.SIGN_UP_SCREEN -> {
                replaceFragment(R.id.auth_container, SignUpFragment.newInstance(), isRootChild, isRootChild)

            }
            IAuthFlow.NavigationType.RECOVER_ACCOUNT_SCREEN -> {
                replaceFragment(R.id.auth_container, RecoverAccountFragment.newInstance(), isRootChild, isRootChild)
            }
        }
    }

    /**
     *
     */
    override fun backRout() {
        super.onBackPressed()
    }

    private fun handleIntent() {
        rootChildType=intent?.extras?.getSerializable(ApplicationConstants.AUTH_TYPE_SCREEN) as IAuthFlow.NavigationType
        openScreen(rootChildType)
        hideKeyboard()
    }
}

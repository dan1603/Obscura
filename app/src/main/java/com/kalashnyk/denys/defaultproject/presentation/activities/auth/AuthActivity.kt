package com.kalashnyk.denys.defaultproject.presentation.activities.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.IAuthFlow
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity
import com.kalashnyk.denys.defaultproject.presentation.fragments.recover_account.RecoverAccountFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.sign_in.SignInFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.sign_up.SignUpFragment
import com.kalashnyk.denys.defaultproject.utils.ApplicationConstants
import com.kalashnyk.denys.defaultproject.utils.extention.hideKeyboard

class AuthActivity : BaseActivity(), IAuthFlow.IAuthListener {

    companion object {
        @JvmStatic
        fun newInstanceWithClearStack(context: Context, typeScreen: IAuthFlow.NavigationType): Intent {
            return Intent(context, AuthActivity::class.java).apply {
                this.putExtra(ApplicationConstants.AUTH_TYPE_SCREEN, typeScreen)
                this.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
    }

    override fun injectDependency(component: ViewModelComponent) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        handleIntent()
    }

    override fun authRequest(flowModel: AuthFlowModel, callback: IAuthFlow.IAuthCallback) {
        when (flowModel.type) {
            IAuthFlow.AuthType.SIGN_IN -> {

            }

            IAuthFlow.AuthType.SIGN_UP -> {

            }

            IAuthFlow.AuthType.RECOVER_ACCOUNT -> {

            }
        }
    }

    override fun socialAuth(type: IAuthFlow.SocialAuthType, callback: IAuthFlow.IAuthCallback) {
        when (type) {
            IAuthFlow.SocialAuthType.FACEBOOK -> {

            }
            IAuthFlow.SocialAuthType.GOOGLE -> {

            }
        }
    }

    override fun openScreen(typeScreen: IAuthFlow.NavigationType) {
        when (typeScreen) {
            IAuthFlow.NavigationType.SIGN_IN_SCREEN -> {
                replaceFragment(R.id.auth_container, SignInFragment.newInstance())
            }
            IAuthFlow.NavigationType.SIGN_UP_SCREEN -> {
                replaceFragment(R.id.auth_container, SignUpFragment.newInstance())

            }
            IAuthFlow.NavigationType.RECOVER_ACCOUNT_SCREEN -> {
                replaceFragment(R.id.auth_container, RecoverAccountFragment.newInstance())
            }
        }
    }

    override fun backRout() {
        super.onBackPressed()
    }

    private fun handleIntent() {
        openScreen(intent?.extras?.getSerializable(ApplicationConstants.AUTH_TYPE_SCREEN) as IAuthFlow.NavigationType)
        hideKeyboard()
    }
}

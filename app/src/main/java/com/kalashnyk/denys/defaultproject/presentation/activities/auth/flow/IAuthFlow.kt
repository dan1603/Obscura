package com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow

import android.text.TextWatcher
import com.kalashnyk.denys.defaultproject.presentation.base.IBaseFlow
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.Pages

interface IAuthFlow : IBaseFlow {

    // for social auth
    enum class SocialAuthType {
        GOOGLE, FACEBOOK
    }

    // for request
    // todo refactor
    enum class AuthType {
        SIGN_IN, SIGN_UP, RECOVER_ACCOUNT
    }

    interface IAuthListener : IBaseFlow.IBaseListener {
        fun authRequest(flowModel: AuthFlowModel, callback: IAuthCallback)
        fun socialAuth(type: SocialAuthType, callback: IAuthCallback?)
        fun openScreen(page: Pages)
    }

    interface IAuthCallback : IBaseFlow.IBaseCallback, TextWatcher {
        fun showError(error: AuthFlowErrorModel)
        fun hideError()
    }
}
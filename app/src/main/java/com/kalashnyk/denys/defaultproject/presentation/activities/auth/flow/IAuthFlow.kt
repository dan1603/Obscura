package com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow

import android.text.TextWatcher
import com.kalashnyk.denys.defaultproject.presentation.base.IBaseFlow

interface IAuthFlow : IBaseFlow {

    // for open screen by type
    //todo refactor
    enum class NavigationType {
        SIGN_IN_SCREEN, SIGN_UP_SCREEN, RECOVER_ACCOUNT_SCREEN
    }

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
        fun openScreen(typeScreen: NavigationType)
    }

    interface IAuthCallback : IBaseFlow.IBaseCallback, TextWatcher {
        fun showError(error: AuthFlowErrorModel)
    }
}
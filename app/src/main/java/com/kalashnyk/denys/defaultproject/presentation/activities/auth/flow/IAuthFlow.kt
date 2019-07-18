package com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow

interface IAuthFlow {

    // for open screen by type
    enum class NavigationType {
        SIGN_IN_SCREEN, SIGN_UP_SCREEN, RECOVER_ACCOUNT_SCREEN
    }

    // for social auth
    enum class SocialAuthType {
        GOOGLE, FACEBOOK
    }

    // for request
    enum class AuthType {
        SIGN_IN, SIGN_UP, RECOVER_ACCOUNT
    }

    interface IAuthListener {
        fun authRequest(flowModel: AuthFlowModel, callback: IAuthCallback)
        fun socialAuth(type: SocialAuthType, callback: IAuthCallback)
        fun openScreen(typeScreen: NavigationType)
    }

    interface IAuthCallback {
        fun showError(error: AuthFlowErrorModel)
    }
}
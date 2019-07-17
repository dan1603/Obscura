package com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow

interface IAuthFlow {

    enum class NavigationType {
        SIGN_IN, SIGN_UP, RECOVER_ACCOUNT
    }

    enum class SocialAuthType {
        GOOGLE, FACEBOOK
    }

    enum class AuthType {
        SIGN_IN, SIGN_UP, RECOVER_ACCOUNT
    }

    interface IAuthListener {
        fun authRequest(flow: AuthFlow, callback: IAuthCallback)
        fun socialAuth(type: SocialAuthType, callback: IAuthCallback)
        fun openScreen(typeScreen: NavigationType)
    }

    interface IAuthCallback {
        fun showError(error: String)
    }
}
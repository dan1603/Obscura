package com.kalashnyk.denys.defaultproject.presentation.activities.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.IAuthFlow

class AuthActivity : AppCompatActivity(), IAuthFlow.IAuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
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

            }
            IAuthFlow.NavigationType.SIGN_UP_SCREEN -> {

            }
            IAuthFlow.NavigationType.RECOVER_ACCOUNT_SCREEN -> {

            }
        }
    }
}

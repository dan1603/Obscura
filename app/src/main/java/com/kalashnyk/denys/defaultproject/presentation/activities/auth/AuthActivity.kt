package com.kalashnyk.denys.defaultproject.presentation.activities.auth

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlow
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.IAuthFlow

class AuthActivity : AppCompatActivity(), IAuthFlow.IAuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    override fun authRequest(flow: AuthFlow, callback: IAuthFlow.IAuthCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun socialAuth(type: IAuthFlow.SocialAuthType, callback: IAuthFlow.IAuthCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openScreen(typeScreen: IAuthFlow.NavigationType) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

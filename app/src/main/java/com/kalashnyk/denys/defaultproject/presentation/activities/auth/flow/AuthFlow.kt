package com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow

import android.text.TextWatcher
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFlow
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.Pages

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
interface AuthFlow : BaseFlow {

    /**
     * for social auth
     */
    enum class SocialAuthType {

        /**
         *
         */
        GOOGLE,

        /**
         *
         */
        FACEBOOK
    }

    /**
     *
     */
    interface AuthListener : BaseFlow.BaseListener {
        fun routBack()
        fun authRequest(flowModel: AuthFlowModel, callback: AuthCallback)
        fun socialAuth(type: SocialAuthType, callback: AuthCallback?)
    }

    /**
     *
     */
    interface AuthCallback : BaseFlow.IBaseCallback, TextWatcher {
        fun showError(error: AuthFlowErrorModel)
        fun hideError()
    }
}
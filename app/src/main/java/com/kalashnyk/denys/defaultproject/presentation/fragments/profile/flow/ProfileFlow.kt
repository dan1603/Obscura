package com.kalashnyk.denys.defaultproject.presentation.fragments.profile.flow

import com.kalashnyk.denys.defaultproject.presentation.base.BaseFlow

interface ProfileFlow : BaseFlow {

    interface ProfileListener : BaseFlow.BaseListener {
        fun onLogout()
    }
}
package com.kalashnyk.denys.defaultproject.presentation.base

import android.os.Bundle
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowModel
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment

abstract class BaseAuthFragment: BaseFragment() {

    protected var authFlowModel : AuthFlowModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authFlowModel = AuthFlowModel()
    }
}
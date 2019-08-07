package com.kalashnyk.denys.defaultproject.presentation.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowModel
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment

abstract class BaseAuthFragment<V : ViewDataBinding>: BaseFragment<V>() {

    protected var authFlowModel : AuthFlowModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authFlowModel = AuthFlowModel()
    }


}
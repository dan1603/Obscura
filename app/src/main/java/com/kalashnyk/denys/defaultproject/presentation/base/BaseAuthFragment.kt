package com.kalashnyk.denys.defaultproject.presentation.base

import android.content.Context
import android.os.Bundle
import android.text.TextWatcher
import androidx.databinding.ViewDataBinding
import com.google.android.material.textfield.TextInputLayout
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthChildCases
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthChildCasesBindingModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.IAuthFlow
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment

abstract class BaseAuthFragment<V : ViewDataBinding>: BaseFragment<V>(), IAuthFlow.IAuthCallback {

    protected var bindingModel : AuthChildCasesBindingModel? = null
    protected var listener: IAuthFlow.IAuthListener? = null
    protected var authChildCases : AuthChildCases? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupTypeScreen()
        prepareBindingModel()
    }

    /**
     * @param context
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IAuthFlow.IAuthListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    /**
     *
     */
    override fun onDetach() {
        super.onDetach()
        bindingModel = null
        listener = null
        authChildCases = null
    }

    abstract fun prepareBindingModel()

    abstract fun setupTypeScreen()
}
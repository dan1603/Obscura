package com.kalashnyk.denys.defaultproject.presentation.base

import android.content.Context
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthChildCasesBindingModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthTextWatcher
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.IAuthFlow

abstract class BaseAuthFragment<V : ViewDataBinding>: BaseFragment<V>(), IAuthFlow.IAuthCallback {

    protected var bindingModel : AuthChildCasesBindingModel? = null
    protected var listener: IAuthFlow.IAuthListener? = null
    protected lateinit var authChildCases : AuthFlowModel
    protected lateinit var textWatcher: AuthTextWatcher

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
        authChildCases.deleteObservers()
    }

    abstract fun prepareBindingModel()

    abstract fun setupTypeScreen()

}
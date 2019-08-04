package com.kalashnyk.denys.defaultproject.presentation.fragments.recover_account

import android.content.Context
import android.os.Bundle

import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowErrorModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.IAuthFlow
import com.kalashnyk.denys.defaultproject.presentation.base.BaseAuthFragment

class RecoverAccountFragment : BaseAuthFragment(), IAuthFlow.IAuthCallback {

    private var listener: IAuthFlow.IAuthListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //ToDo get extras from bundle
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IAuthFlow.IAuthListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun getLayout(): Int  = R.layout.fragment_recover_account

    override fun showError(error: AuthFlowErrorModel) {
        //ToDo show error for validation inputs
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RecoverAccountFragment().apply {
                arguments = Bundle().apply {
                    //ToDo put bundle
                }
            }
    }
}

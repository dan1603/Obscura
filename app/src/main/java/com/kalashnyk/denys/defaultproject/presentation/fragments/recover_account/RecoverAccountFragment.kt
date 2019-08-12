package com.kalashnyk.denys.defaultproject.presentation.fragments.recover_account

import android.content.Context
import android.os.Bundle
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.RecoverAccountDataBinding
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowErrorModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.IAuthFlow
import com.kalashnyk.denys.defaultproject.presentation.base.BaseAuthFragment

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class RecoverAccountFragment : BaseAuthFragment<RecoverAccountDataBinding>(), IAuthFlow.IAuthCallback {

    private var listener: IAuthFlow.IAuthListener? = null

    /**
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //ToDo get extras from bundle
        }
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
        listener = null
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int = R.layout.fragment_recover_account

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: RecoverAccountDataBinding) {
        binding.listener = listener
    }

    /**
     * @param error
     */
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

package com.kalashnyk.denys.defaultproject.presentation.fragments.recover_account

import android.os.Bundle
import android.text.Editable
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.RecoverAccountDataBinding
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.*
import com.kalashnyk.denys.defaultproject.presentation.base.BaseAuthFragment

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class RecoverAccountFragment : BaseAuthFragment<RecoverAccountDataBinding>(), IAuthFlow.IAuthCallback {

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
     *
     */
    override fun setupTypeScreen(){
        authChildCases =  AuthFlowModel(IAuthFlow.AuthType.RECOVER_ACCOUNT)
    }

    /**
     *
     */
    override fun prepareBindingModel(){
        bindingModel = AuthChildCasesBindingModel(authChildCases, listener,this)
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int = R.layout.fragment_recover_account

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: RecoverAccountDataBinding) {
        bindingModel?.apply {
            binding.bindingModel = this
        }
        binding.etAuthEmail.addTextChangedListener(AuthTextWatcher(this, authChildCases, binding.etAuthEmail))

    }

    /**
     * @param error
     */
    override fun showError(error: AuthFlowErrorModel) {
        authChildCases.apply {
            this.error = error
        }
    }

    override fun hideError() {
        authChildCases.error= AuthFlowErrorModel()
    }

    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        authChildCases.error= AuthFlowErrorModel()
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

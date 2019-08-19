package com.kalashnyk.denys.defaultproject.presentation.fragments.sign_in

import android.os.Bundle
import android.text.Editable
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.SignInDataBinding
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthChildCases
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthChildCasesBindingModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowErrorModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.IAuthFlow
import com.kalashnyk.denys.defaultproject.presentation.base.BaseAuthFragment

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class SignInFragment : BaseAuthFragment<SignInDataBinding>() {

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
        authChildCases =  AuthChildCases(IAuthFlow.AuthType.SIGN_IN)
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
    override fun getLayoutId(): Int = R.layout.fragment_sign_in

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: SignInDataBinding) {
        bindingModel?.apply {
            binding.bindingModel = this
            this.bindTilEmail(binding.tilSignInEmail)
            this.bindTilPassword(binding.tilSignInPassword)
        }
        binding.tilSignInEmail.editText?.addTextChangedListener(this)
        binding.tilSignInPassword.editText?.addTextChangedListener(this)
    }

    /**
     * @param error
     */
    override fun showError(error: AuthFlowErrorModel) {
        authChildCases.apply {
            this.error = error
        }
    }

    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
       authChildCases.error= AuthFlowErrorModel()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SignInFragment().apply {
                arguments = Bundle().apply {
                    //ToDo put bundle
                }
            }
    }
}

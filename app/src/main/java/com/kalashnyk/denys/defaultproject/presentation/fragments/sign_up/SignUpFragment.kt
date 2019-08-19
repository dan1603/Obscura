package com.kalashnyk.denys.defaultproject.presentation.fragments.sign_up

import android.os.Bundle
import android.text.Editable
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.SignUpDataBinding
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthChildCases
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthChildCasesBindingModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowErrorModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.IAuthFlow
import com.kalashnyk.denys.defaultproject.presentation.base.BaseAuthFragment
import android.view.View

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class SignUpFragment : BaseAuthFragment<SignUpDataBinding>(), IAuthFlow.IAuthCallback {

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
    override fun setupTypeScreen() {
        authChildCases = AuthChildCases(IAuthFlow.AuthType.SIGN_UP)
    }

    /**
     *
     */
    override fun prepareBindingModel() {
        bindingModel=AuthChildCasesBindingModel(authChildCases, listener, this)
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int= R.layout.fragment_sign_up

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: SignUpDataBinding) {
        bindingModel?.apply {
            binding.bindingModel = this
            this.bindTilEmail(binding.tilSignUpEmail)
            this.bindTilPassword(binding.tilSignUpPassword)
            this.bindTilConfirmPassword(binding.tilSignUpConfirmPassword)
            this.bindCheckBoxTermsConditions(binding.checkBoxSignUpAgree)
        }

        binding.tilSignUpEmail.editText?.addTextChangedListener(this)
        binding.tilSignUpPassword.editText?.addTextChangedListener(this)
        binding.tilSignUpConfirmPassword.editText?.addTextChangedListener(this)
        binding.checkBoxSignUpAgree.setOnClickListener(View.OnClickListener {
            authChildCases.error = AuthFlowErrorModel()
        })

    }

    /**
     * @param error
     */
    override fun showError(error: AuthFlowErrorModel) {
        authChildCases.apply {
            this.error=error
        }
    }

    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        authChildCases.error= AuthFlowErrorModel()
    }

    companion object {
        @JvmStatic
        fun newInstance()=
            SignUpFragment().apply {
                arguments=Bundle().apply {
                    //todo put bundle
                }
            }
    }
}

package com.kalashnyk.denys.defaultproject.presentation.fragments.sign_up

import android.os.Bundle
import android.text.Editable
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.SignUpDataBinding
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.*
import com.kalashnyk.denys.defaultproject.presentation.base.BaseAuthFragment
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.Pages

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
        authChildCases = AuthFlowModel(Pages.SIGN_UP)
    }

    /**
     *
     */
    override fun prepareBindingModel() {
        bindingModel=AuthFlowModelBinding(authChildCases, listener, this)
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
        }

        binding.etAuthEmail.addTextChangedListener(AuthTextWatcher(this, authChildCases, binding.etAuthEmail))
        binding.etAuthPassword.addTextChangedListener(AuthTextWatcher(this, authChildCases, binding.etAuthPassword))
        binding.etAuthConfirmPassword.addTextChangedListener(AuthTextWatcher(this, authChildCases, binding.etAuthConfirmPassword))

        binding.checkBoxSignUpAgree.setOnClickListener {
            authChildCases.agreeTerms = binding.checkBoxSignUpAgree.isChecked
            authChildCases.error = AuthFlowErrorModel()
        }

    }

    /**
     * @param error
     */
    override fun showError(error: AuthFlowErrorModel) {
        authChildCases.apply {
            this.error=error
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
        fun newInstance()=
            SignUpFragment().apply {
                arguments=Bundle().apply {
                    //todo put bundle
                }
            }
    }
}

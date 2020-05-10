package com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.kalashnyk.denys.defaultproject.BR
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.Pages
import com.kalashnyk.denys.defaultproject.utils.binding.TextSpanModel
import com.kalashnyk.denys.defaultproject.utils.extention.*
import com.kalashnyk.denys.defaultproject.utils.validation.ValidationErrorMessage
import java.util.*

/**
 *
 */
class AuthFlowModelBinding(
    private var authFlowModel: AuthFlowModel,
    private var listener: AuthFlow.AuthListener?,
    private var callback: AuthFlow.AuthCallback
) : Observer, BaseObservable() {

    private var context: Context

    init {
        authFlowModel.addObserver(this)
        context=listener as Context
    }

    /**
     * @field signUpTextSpanModel
     */
    val signUpTextSpanModel: TextSpanModel
        @Bindable get() {
            return TextSpanModel(
                context.resources.getString(R.string.sign_up),
                context.resources.getString(R.string.dont_have_an_account),
                TextSpanModel.SpanTextPosition.LAST,
                R.dimen.txt_size_16,
                R.dimen.txt_size_14,
                R.color.colorWhite,
                R.color.dark_blue,
                Typeface.NORMAL
            )
        }

    /**
     * @field signInTextSpanModel
     */
    val signInTextSpanModel: TextSpanModel
        @Bindable get() {
            return TextSpanModel(
                context.resources.getString(R.string.sign_in),
                context.resources.getString(R.string.text_have_an_account),
                TextSpanModel.SpanTextPosition.LAST,
                R.dimen.txt_size_16,
                R.dimen.txt_size_14,
                R.color.colorWhite,
                R.color.dark_blue,
                Typeface.NORMAL
            )
        }

    /**
     * @field termsConditionsTextSpanModel
     */
    val termsConditionsTextSpanModel: TextSpanModel
        @Bindable get() {
            return TextSpanModel(
                context.resources.getString(R.string.text_terms_and_privacy),
                context.resources.getString(R.string.text_i_agree_with),
                TextSpanModel.SpanTextPosition.LAST,
                R.dimen.txt_size_12,
                R.dimen.txt_size_12,
                R.color.colorWhite,
                R.color.dark_blue,
                Typeface.NORMAL
            )
        }

    /**
     * @field agreeTerms
     */
    var agreeTerms: Boolean=false
        set(value) {
            field=value
            authFlowModel.agreeTerms=field
            notifyPropertyChanged(BR.agreeTerms)
        }
        @Bindable get() {
            return authFlowModel.agreeTerms
        }

    /**
     * @field authFlowError
     */
    var authFlowError: AuthFlowErrorModel=AuthFlowErrorModel()
        set(value) {
            field=value
            notifyPropertyChanged(BR.authFlowError)
        }
        @Bindable get() {
            return authFlowModel.error
        }

    /**
     * @param o
     * @param arg
     */
    override fun update(o: Observable?, arg: Any?) {
        if (o is AuthFlowModel?) {
            if (arg == errorField) authFlowError=authFlowModel.error
        }
    }

    /**
     *
     */
    fun onOpenScreen(typeNavigate: Pages, view: View) {
        view.hideKeyboardWithClearFocus()
        listener?.openScreen(typeNavigate)
    }

    /**
     *
     */
    fun onSocialAuth(type: AuthFlow.SocialAuthType, view: View) {
        view.hideKeyboardWithClearFocus()
        listener?.socialAuth(type, callback)
    }

    /**
     *
     */
    fun onAuthRequest(view: View) {
        view.hideKeyboardWithClearFocus()
        listener?.authRequest(authFlowModel, callback)
    }

    /**
     *
     */
    fun onTermsChecked(view: View) {
        view.hideKeyboardWithClearFocus()
    }

    /**
     *
     */
    fun onRoutBack() {
        listener?.routBack()
    }

    companion object {

        @JvmStatic
        @BindingAdapter(
            "bind:authError"
        )
        fun bindError(
            viewGroup: LinearLayout,
            authError: AuthFlowErrorModel
        ) {
            if (authError.hasErrors()) {
                handelAuthFlowError(
                    authError,
                    viewGroup.findViewById(R.id.tilAuthEmail),
                    viewGroup.findViewById(R.id.tilAuthPassword),
                    viewGroup.findViewById(R.id.tilAuthConfirmPassword),
                    viewGroup.findViewById(R.id.checkBoxSignUpAgree),
                    viewGroup.findViewById(R.id.tvFlowError)
                )
            } else {
                clearAuthFlowError(
                    viewGroup.findViewById(R.id.tilAuthEmail),
                    viewGroup.findViewById(R.id.tilAuthPassword),
                    viewGroup.findViewById(R.id.tilAuthConfirmPassword),
                    viewGroup.findViewById(R.id.checkBoxSignUpAgree),
                    viewGroup.findViewById(R.id.tvFlowError)
                )
            }
        }

        /**
         * @param tvFlowError for Api error
         */
        private fun handelAuthFlowError(
            errorModel: AuthFlowErrorModel,
            tilEmail: TextInputLayout?,
            tilPassword: TextInputLayout?,
            tilConfirmPassword: TextInputLayout?,
            checkBoxTermsConditions: CheckBox?,
            tvFlowError: TextView?
        ) {
            errorModel.errors.forEach { (key: AuthFlowErrorModel.ErrorType, value: ValidationErrorMessage) ->
                when (key) {
                    AuthFlowErrorModel.ErrorType.EMAIL_ERROR -> {
                        tilEmail?.addErrorForTextInputLayout(
                            value.toString(tilEmail.context)
                        )
                    }
                    AuthFlowErrorModel.ErrorType.PASSWORD_ERROR -> {
                        tilPassword?.addErrorForTextInputLayout(
                            value.toString(tilPassword.context)
                        )
                    }

                    AuthFlowErrorModel.ErrorType.PASSWORD_CONFIRM_ERROR -> {
                        tilConfirmPassword?.addErrorForTextInputLayout(
                            value.toString(tilConfirmPassword.context)
                        )
                    }
                    AuthFlowErrorModel.ErrorType.TERMS_CONDITION_ERROR -> {
                        checkBoxTermsConditions?.addErrorForCheckBox()
                    }
                }
            }
        }

        /**
         * @param tvFlowError for Api error
         */
        private fun clearAuthFlowError(
            tilEmail: TextInputLayout?,
            tilPassword: TextInputLayout?,
            tilConfirmPassword: TextInputLayout?,
            checkBoxTermsConditions: CheckBox?,
            tvFlowError: TextView?
        ) {
            tilEmail?.clearErrorForTextInputLayout()
            tilPassword?.clearErrorForTextInputLayout()
            tilConfirmPassword?.clearErrorForTextInputLayout()
            checkBoxTermsConditions?.clearErrorForCheckBox()
            tvFlowError?.clearErrorForTextView()
        }
    }
}
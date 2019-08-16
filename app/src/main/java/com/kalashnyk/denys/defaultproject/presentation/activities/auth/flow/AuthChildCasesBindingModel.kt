package com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow

import android.os.Build
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.kalashnyk.denys.defaultproject.BR
import com.kalashnyk.denys.defaultproject.R
import java.util.*

class AuthChildCasesBindingModel(
    private var authChild: AuthChildCases?,
    private var listener: IAuthFlow.IAuthListener?,
    private var callback: IAuthFlow.IAuthCallback
) : Observer, BaseObservable() {

    init {
        authChild?.addObserver(this)
    }

    var typeChild: IAuthFlow.AuthType? = authChild?.typeChild

    var tilEmail: TextInputLayout? = null
    var tilPassword: TextInputLayout? = null
    var tilConfirmPassword: TextInputLayout? = null
    var checkBoxTermsConditions: CheckBox? = null

    fun bindTilEmail(tilEmail: TextInputLayout){
        this.tilEmail = tilEmail
    }

    fun bindTilPassword(tilPassword: TextInputLayout){
        this.tilPassword = tilPassword
    }

    fun bindTilConfirmPassword(tilConfirmPassword: TextInputLayout){
        this.tilConfirmPassword = tilConfirmPassword
    }

    fun bindCheckBoxTermsConditions(checkBoxTermsConditions: CheckBox){
        this.checkBoxTermsConditions = checkBoxTermsConditions
    }

    var authFlowError: AuthFlowErrorModel? = authChild?.error
        @Bindable get() {
            return authChild?.error
        }

    override fun update(o: Observable?, arg: Any?) {
        if (arg is AuthFlowErrorModel) {
            val error = o as AuthFlowErrorModel
            if(authFlowError?.type != error.type){
                authFlowError = error
                notifyPropertyChanged(BR.authFlowError)
            }
        }
    }

    fun onOpenScreen(typeNavigate: IAuthFlow.NavigationType) = listener?.openScreen(typeNavigate)

    fun onSocialAuth(type: IAuthFlow.SocialAuthType) = listener?.socialAuth(type, callback)

    fun onAuthRequest() {
        typeChild?.let {
            val authFlowModel = AuthFlowModel(it)

            authFlowModel.email = tilEmail?.editText?.text.toString()

            if (it == IAuthFlow.AuthType.SIGN_UP) {
                authFlowModel.password = tilPassword?.editText?.text.toString()
                authFlowModel.confirmPassword = tilConfirmPassword?.editText?.text.toString()
                authFlowModel.isAcceptTerms = checkBoxTermsConditions?.isChecked
            } else if (it == IAuthFlow.AuthType.SIGN_IN) {
                authFlowModel.password = tilPassword?.editText?.text.toString()
            }

            listener?.authRequest(authFlowModel, callback)
        }
    }

    companion object {

        @JvmStatic
        @BindingAdapter("bind:authError")
        fun LinearLayout.bindError(
            model: AuthChildCasesBindingModel?
        ) {
            if (model?.authFlowError?.type != AuthFlowErrorModel.AuthFlowErrorType.DEFAULT_ERROR) {
                model?.authFlowError?.let {
                    handelAuthFlowError(
                        it,
                        model.tilEmail,
                        model.tilPassword,
                        model.tilConfirmPassword,
                        model.checkBoxTermsConditions
                    )
                }
            } else {
                clearAuthFlowError(
                    model.tilEmail,
                    model.tilPassword,
                    model.tilConfirmPassword,
                    model.checkBoxTermsConditions
                )
            }
        }

        private fun handelAuthFlowError(
            errorModel: AuthFlowErrorModel,
            tilEmail: TextInputLayout?,
            tilPassword: TextInputLayout?,
            tilConfirmPassword: TextInputLayout?,
            checkBoxTermsConditions: CheckBox?
        ) {
            when (errorModel.type) {
                AuthFlowErrorModel.AuthFlowErrorType.EMAIL_ERROR -> {
                    if (tilEmail != null) showAuthFlowError(errorModel, tilEmail)
                }
                AuthFlowErrorModel.AuthFlowErrorType.PASSWORD_ERROR -> {
                    if (tilPassword != null) showAuthFlowError(errorModel, tilPassword)
                }
                AuthFlowErrorModel.AuthFlowErrorType.PASSWORD_CONFIRM_ERROR -> {
                    if (tilConfirmPassword != null) showAuthFlowError(errorModel, tilConfirmPassword)
                }
                AuthFlowErrorModel.AuthFlowErrorType.TERMS_CONDITION_ERROR -> {
                    if (checkBoxTermsConditions != null) showAuthFlowError(errorModel, checkBoxTermsConditions)
                }
                AuthFlowErrorModel.AuthFlowErrorType.SIGN_UP_ERRORS -> {
                    if (tilConfirmPassword != null && checkBoxTermsConditions != null) {
                        showAuthFlowError(errorModel, tilConfirmPassword)
                        showAuthFlowError(errorModel, checkBoxTermsConditions)
                    }
                }
                AuthFlowErrorModel.AuthFlowErrorType.SIGN_IN_ERRORS -> {
                    if (tilPassword != null) showAuthFlowError(errorModel, tilPassword)
                }
            }
        }

        private fun <V : View> showAuthFlowError(
            errorModel: AuthFlowErrorModel,
            view: V
        ) {
            if (view is TextInputLayout) {
                view.isHintEnabled = false
                view.error = errorModel.message?.toString(view.context)
            } else if (view is CheckBox) {
                view.background = view.context.getDrawable(R.drawable.ic_check_box_error)
            }
        }

        private fun clearAuthFlowError(
            tilEmail: TextInputLayout?,
            tilPassword: TextInputLayout?,
            tilConfirmPassword: TextInputLayout?,
            checkBoxTermsConditions: CheckBox?
        ) {
            tilEmail?.isHintEnabled = true
            tilEmail?.error = null
            tilPassword?.isHintEnabled = true
            tilPassword?.error = null
            tilConfirmPassword?.isHintEnabled = true
            tilConfirmPassword?.error = null
            checkBoxTermsConditions?.let {
                if (it.isChecked) {
                    it.background = it.context.getDrawable(R.drawable.ic_check_box_active)

                } else {
                    it.background = it.context.getDrawable(R.drawable.ic_check_box_normal)
                }
            }
        }
    }
}
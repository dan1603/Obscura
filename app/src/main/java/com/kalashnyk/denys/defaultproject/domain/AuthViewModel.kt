package com.kalashnyk.denys.defaultproject.domain

import androidx.lifecycle.ViewModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowErrorModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.IAuthFlow
import com.kalashnyk.denys.defaultproject.utils.validation.ValidationHandler
import com.kalashnyk.denys.defaultproject.utils.validation.ValidationHandlerImpl

/**
 *
 */
class AuthViewModel : ViewModel() {

    private val validationHandler: ValidationHandler=ValidationHandlerImpl()

    /**
     *
     */
    fun authRequest(flowModel: AuthFlowModel, callback: IAuthFlow.IAuthCallback) {
        val validation: AuthFlowErrorModel=doValidation(flowModel)
        if (validation.emptyErrors()) {

        } else {
            callback.showError(validation)
        }
    }

    /**
     *
     */
    override fun onCleared() {
        super.onCleared()
    }

    private fun doValidation(flowModel: AuthFlowModel): AuthFlowErrorModel=
        validationHandler.validationAuthCases(
            flowModel.email,
            flowModel.password,
            flowModel.passwordConfirm,
            flowModel.agreeTerms
        )
}
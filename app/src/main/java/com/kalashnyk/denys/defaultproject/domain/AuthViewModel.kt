package com.kalashnyk.denys.defaultproject.domain

import androidx.lifecycle.ViewModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowErrorModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.IAuthFlow
import com.kalashnyk.denys.defaultproject.utils.validation.IValidationHandler
import com.kalashnyk.denys.defaultproject.utils.validation.ValidationHandlerImpl

class AuthViewModel : ViewModel() {

    private val validationHandler : IValidationHandler = ValidationHandlerImpl()

    fun authRequest(flowModel: AuthFlowModel, callback: IAuthFlow.IAuthCallback){
        val validation: Pair<Boolean, AuthFlowErrorModel> = doValidation(flowModel)
        if(validation.first){

        }else {
            callback.showError(validation.second)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    private fun doValidation(flowModel: AuthFlowModel) : Pair<Boolean, AuthFlowErrorModel> {
        val checkData : Pair<Boolean, AuthFlowErrorModel>
        when(flowModel.typeChild){
            IAuthFlow.AuthType.SIGN_UP -> {
                checkData = validationHandler.validationSignUpCases(
                    flowModel.email,
                    flowModel.password,
                    flowModel.passwordConfirm,
                    flowModel.agreeTerms
                )
            }
            IAuthFlow.AuthType.SIGN_IN -> {
                checkData = validationHandler.validationSignInCases(
                    flowModel.email,
                    flowModel.password
                )
            }
            IAuthFlow.AuthType.RECOVER_ACCOUNT -> {
                checkData = validationHandler.validationRecoverAccountCases(
                    flowModel.email)
            }
        }

        return checkData
    }
}
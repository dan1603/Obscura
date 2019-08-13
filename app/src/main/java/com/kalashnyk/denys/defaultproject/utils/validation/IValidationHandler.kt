package com.kalashnyk.denys.defaultproject.utils.validation

import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.AuthFlowErrorModel
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow.IAuthFlow

/**
 * source for handling validation errors
 * with ability return errors to view between callback
 */
interface IValidationHandler {

    /**
     * @param email
     * @param password
     * @param callback
     */
    fun validationSignInCases(
        email: CharSequence,
        password: CharSequence
    ): Pair<Boolean, AuthFlowErrorModel>

    /**
     * @param email
     * @param password
     * @param confirmPassword
     * @param isAgreementPolicySecurity
     * @param callback
     */
    fun validationSignUpCases(
        email: CharSequence,
        password: CharSequence,
        confirmPassword: CharSequence,
        isAgreementPolicySecurity: Boolean
    ): Pair<Boolean, AuthFlowErrorModel>

    /**
     * @param email
     * @param callback
     */
    fun validationRecoverAccountCases(
        email: CharSequence,
        callback: IAuthFlow.IAuthCallback
    ): Pair<Boolean, AuthFlowErrorModel>
}

internal class ValidationHandlerImpl : IValidationHandler {

    private val validator: IValidator = ValidatorImpl()

    override fun validationSignInCases(
        email: CharSequence,
        password: CharSequence
    ): Pair<Boolean, AuthFlowErrorModel> {

        val error = AuthFlowErrorModel()

        if (email.trim().isEmpty() &&
            password.trim().isEmpty() &&
            !validator.isValidEmail(email) &&
            !validator.isValidPassword(password)
        ) {
            error.type = AuthFlowErrorModel.AuthFlowErrorType.SIGN_IN_ERRORS
            error.message = ValidationErrorMessage.FLOW_SIGN_IN_VALIDATION_ERROR
            return Pair(false, error)
        } else if (email.trim().isEmpty()) {
            error.type = AuthFlowErrorModel.AuthFlowErrorType.EMAIL_ERROR
            error.message = ValidationErrorMessage.EMAIL_BLANK_VALIDATION_ERROR
            return Pair(false, error)
        } else if (!validator.isValidEmail(email)) {
            error.type = AuthFlowErrorModel.AuthFlowErrorType.EMAIL_ERROR
            error.message = ValidationErrorMessage.EMAIL_VALIDATION_ERROR
            return Pair(false, error)
        } else if (password.trim().isEmpty()) {
            error.type = AuthFlowErrorModel.AuthFlowErrorType.PASSWORD_ERROR
            error.message = ValidationErrorMessage.PASSWORD_BLANK_VALIDATION_ERROR
            return Pair(false, error)
        } else if (!validator.isValidPassword(password)) {
            error.type = AuthFlowErrorModel.AuthFlowErrorType.PASSWORD_ERROR
            error.message = ValidationErrorMessage.PASSWORD_VALIDATION_ERROR
            return Pair(false, error)
        } else {
            return Pair(true, error)
        }
    }

    override fun validationSignUpCases(
        email: CharSequence,
        password: CharSequence,
        confirmPassword: CharSequence,
        isAgreementPolicySecurity: Boolean
    ): Pair<Boolean, AuthFlowErrorModel> {

        val error = AuthFlowErrorModel()

        if (email.trim().isEmpty() &&
            password.trim().isEmpty() &&
            !validator.isValidEmail(email) &&
            !validator.isValidPassword(password) &&
            !isAgreementPolicySecurity &&
            !validator.isValidPassword(confirmPassword)
        ) {
            error.type = AuthFlowErrorModel.AuthFlowErrorType.SIGN_UP_ERRORS
            error.message = ValidationErrorMessage.FLOW_SIGN_IN_VALIDATION_ERROR
            return Pair(false, error)
        } else if (email.trim().isEmpty()) {
            error.type = AuthFlowErrorModel.AuthFlowErrorType.EMAIL_ERROR
            error.message = ValidationErrorMessage.EMAIL_BLANK_VALIDATION_ERROR
            return Pair(false, error)
        } else if (!validator.isValidEmail(email)) {
            error.type = AuthFlowErrorModel.AuthFlowErrorType.EMAIL_ERROR
            error.message = ValidationErrorMessage.EMAIL_VALIDATION_ERROR
            return Pair(false, error)
        } else if (password.trim().isEmpty()) {
            error.type = AuthFlowErrorModel.AuthFlowErrorType.PASSWORD_ERROR
            error.message = ValidationErrorMessage.PASSWORD_BLANK_VALIDATION_ERROR
            return Pair(false, error)
        } else if (!validator.isValidPassword(password)) {
            error.type = AuthFlowErrorModel.AuthFlowErrorType.PASSWORD_ERROR
            error.message = ValidationErrorMessage.PASSWORD_VALIDATION_ERROR
            return Pair(false, error)
        } else if (!validator.isValidConfirmPassword(password, confirmPassword)) {
            error.type = AuthFlowErrorModel.AuthFlowErrorType.PASSWORD_CONFIRM_ERROR
            error.message = ValidationErrorMessage.PASSWORD_NOT_SAME_VALIDATION_ERROR
            return Pair(false, error)
        } else if (!isAgreementPolicySecurity) {
            error.type = AuthFlowErrorModel.AuthFlowErrorType.TERMS_CONDITION_ERROR
            error.message = ValidationErrorMessage.TERMS_CONDITION_VALIDATION_ERROR
            return Pair(false, error)
        } else {
            return Pair(true, error)
        }
    }

    override fun validationRecoverAccountCases(
        email: CharSequence,
        callback: IAuthFlow.IAuthCallback
    ): Pair<Boolean, AuthFlowErrorModel> {

        val error = AuthFlowErrorModel()

        if (email.trim().isEmpty()) {
            error.type = AuthFlowErrorModel.AuthFlowErrorType.EMAIL_ERROR
            error.message = ValidationErrorMessage.EMAIL_BLANK_VALIDATION_ERROR
            return Pair(false, error)
        } else if (!validator.isValidEmail(email)) {
            error.type = AuthFlowErrorModel.AuthFlowErrorType.EMAIL_ERROR
            error.message = ValidationErrorMessage.EMAIL_VALIDATION_ERROR
            return Pair(false, error)
        } else {
            return Pair(true, error)
        }
    }
}

package com.kalashnyk.denys.defaultproject.utils.validation

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
        password: CharSequence,
        callback: IAuthFlow.IAuthCallback
    ): Boolean

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
        isAgreementPolicySecurity: Boolean,
        callback: IAuthFlow.IAuthCallback
    ): Boolean

    /**
     * @param email
     * @param callback
     */
    fun validationRecoverAccountCases(
        email: CharSequence,
        callback: IAuthFlow.IAuthCallback
    ): Boolean
}

internal class ValidationHandlerImpl : IValidationHandler {

    override fun validationSignInCases(
        email: CharSequence,
        password: CharSequence,
        callback: IAuthFlow.IAuthCallback
    ) : Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validationSignUpCases(
        email: CharSequence,
        password: CharSequence,
        confirmPassword: CharSequence,
        isAgreementPolicySecurity: Boolean,
        callback: IAuthFlow.IAuthCallback
    ) : Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validationRecoverAccountCases(
        email: CharSequence,
        callback: IAuthFlow.IAuthCallback
    ) : Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}

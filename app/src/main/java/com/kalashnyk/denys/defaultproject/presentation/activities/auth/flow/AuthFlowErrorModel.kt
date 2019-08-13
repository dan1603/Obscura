package com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow

/**
 * error of validation and api
 */
class AuthFlowErrorModel(
    /**
     * type
     */
    var type : AuthFlowErrorType,
    /**
     * message body
     */
    var message : String) {

    /**
     * type of error for handling in UI
     * todo maybe need create rout type for navigation
     */
    enum class AuthFlowErrorType{

        /**
         * when validation fail email field
         */
        EMAIL_ERROR,

        /**
         * when validation fail password field
         */
        PASSWORD_ERROR,

        /**
         * when validation fail with password and confirm password fields
         */
        PASSWORD_CONFIRM_ERROR,

        /**
         * when user don't accept terms conditions
         */
        TERMS_CONDITION_ERROR,

        /**
         * when user don't fill all fields of sign up screen
         */
        SIGN_UP_ERRORS,
        /**
         * when user don't fill all fields of sign in screen
         */
        SIGN_IN_ERRORS,
    }
}
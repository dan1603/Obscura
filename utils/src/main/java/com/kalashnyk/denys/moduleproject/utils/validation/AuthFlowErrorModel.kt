package com.kalashnyk.denys.moduleproject.utils.validation

import kotlin.collections.HashMap

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 * error of validation and api
 */
class AuthFlowErrorModel(
    /**
     * errors body
     */
    var errors : Map<ErrorType, ValidationErrorMessage> = HashMap()
) {
    constructor() : this(HashMap())

    /**
     *
     */
    fun hasErrors() : Boolean = errors.isNotEmpty()

    /**
     *
     */
    fun emptyErrors() : Boolean = errors.isEmpty()

    /**
     *
     */
    enum class ErrorType{

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
    }
}
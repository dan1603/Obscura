package com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow

import com.kalashnyk.denys.defaultproject.utils.validation.ValidationErrorMessage
import kotlin.collections.HashMap

/**
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
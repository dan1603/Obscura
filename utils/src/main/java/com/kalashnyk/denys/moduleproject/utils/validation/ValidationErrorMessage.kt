package com.kalashnyk.denys.moduleproject.utils.validation

import android.content.Context
import androidx.annotation.StringRes
import com.kalashnyk.denys.moduleproject.utils.R

/**
 * error of validation and network state
 */
enum class ValidationErrorMessage(
    /**
     * message string resources id
     */
    @StringRes var resource: Int
) {
    /**
     * thrown when connection fails
     */
    CONNECTION_VALIDATION_ERROR(R.string.error_internet_connection),
    /**
     * thrown when network fails
     */
    NETWORK_VALIDATION_ERROR(R.string.error_network_message),
    /**
     * thrown when user password is blank
     */
    PASSWORD_BLANK_VALIDATION_ERROR(R.string.error_password_message),
    /**
     * thrown when user password is incorrect
     */
    PASSWORD_VALIDATION_ERROR(R.string.error_password_message),
    /**
     * thrown when user password and confirm password is different
     */
    PASSWORD_NOT_SAME_VALIDATION_ERROR(R.string.error_password_not_same_message),
    /**
     * thrown when user old password is same
     */
    WRONG_OLD_PASSWORD_VALIDATION_ERROR(R.string.error_wrong_old_password),
    /**
     * thrown when user email is blank
     */
    EMAIL_BLANK_VALIDATION_ERROR(R.string.error_email_blank_message),
    /**
     * thrown when user email is incorrect
     */
    EMAIL_VALIDATION_ERROR(R.string.error_email_message),

    /**
     *  thrown when user email and password invalid or blank
     */
    FLOW_SIGN_IN_VALIDATION_ERROR(R.string.error_flow_sign_in_data),

    /**
     *  thrown when user data invalid or blank
     */
    FLOW_SIGN_UP_VALIDATION_ERROR(R.string.error_flow_sign_up_data),

    /**
     *  thrown when user don't agree terms conditions
     */
    TERMS_CONDITION_VALIDATION_ERROR(R.string.error_terms_conditions),

    /**
     * thrown when user phone is incorrect
     */
    PHONE_VALIDATION_ERROR(R.string.error_phone_message),

    /**
     * thrown when user phone is blank
     */
    PHONE_BLANK_VALIDATION_ERROR(R.string.error_phone_blank_message),
    /**
     * thrown when user confirm phone code fails
     */
    CODE_VALIDATION_ERROR(R.string.error_code_message);
    /**
     * for show message body
     */
    fun toString(context: Context):String = context.getString(resource)
}
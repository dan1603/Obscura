package com.kalashnyk.denys.defaultproject.utils.validation

import android.util.Patterns
import com.kalashnyk.denys.moduleproject.utils.PHONE_LENGTH_WITHOUT_COUNTRY_CODE
import com.kalashnyk.denys.moduleproject.utils.PHONE_LENGTH_WITH_COUNTRY_CODE
import org.apache.commons.lang3.StringUtils
import java.util.regex.Pattern

/**
(?=.*\d) - must contains one digit from 0-9
(?=.*[a-z]) - must contains one lowercase characters
(?=.*[A-Z]) - must contains one uppercase characters
(?=.*[@#$%]) - must contains one special symbols in the list "@#$%"
. -  match anything with previous condition checking
{6,20} - length at least 6 characters and maximum of 20
 */
const val PASSWORD_REGEX: String = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"

/**
 *
 */
val passwordPattern: Pattern = Pattern.compile(PASSWORD_REGEX, Pattern.CASE_INSENSITIVE)

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
interface IValidator {

    /**
     * @param email
     */
    fun isValidEmail(
        email: CharSequence
    ): ValidationErrorMessage?

    /**
     * @param phone
     */
    fun isValidPhone(
        phone: CharSequence
    ): ValidationErrorMessage?

    /**
     * @param phone
     * @param codeCountry
     */
    fun isValidPhoneWithCodeCountry(
        phone: CharSequence,
        codeCountry: CharSequence
    ): ValidationErrorMessage?

    /**
     * @param password
     */
    fun isValidPassword(
        password: CharSequence
    ): ValidationErrorMessage?

    /**
     * @param password
     * @param confirmPassword
     */
    fun isValidConfirmPassword(
        password: CharSequence,
        confirmPassword: CharSequence
    ): ValidationErrorMessage?
}

/**
 *
 */
class ValidatorImpl : IValidator {

    override fun isValidEmail(
        email: CharSequence
    ): ValidationErrorMessage? {
        return when {
            StringUtils.isBlank(email) -> {
                ValidationErrorMessage.EMAIL_BLANK_VALIDATION_ERROR
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                ValidationErrorMessage.EMAIL_VALIDATION_ERROR
            }
            else -> null
        }
    }

    override fun isValidPhone(
        phone: CharSequence
    ): ValidationErrorMessage? {
        return when {
            StringUtils.isBlank(phone) -> {
                ValidationErrorMessage.PHONE_BLANK_VALIDATION_ERROR
            }
            phone.length <= PHONE_LENGTH_WITHOUT_COUNTRY_CODE -> {
                ValidationErrorMessage.PHONE_VALIDATION_ERROR
            }
            else -> null
        }
    }

    override fun isValidPhoneWithCodeCountry(
        phone: CharSequence,
        codeCountry: CharSequence
    ): ValidationErrorMessage? {
        return when {
            StringUtils.isBlank(phone) -> {
                ValidationErrorMessage.PHONE_BLANK_VALIDATION_ERROR
            }

            phone.length <= PHONE_LENGTH_WITH_COUNTRY_CODE -> {
                ValidationErrorMessage.PHONE_VALIDATION_ERROR
            }

            phone.startsWith(codeCountry) -> {
                ValidationErrorMessage.PHONE_VALIDATION_ERROR
            }
            else -> null
        }
    }

    override fun isValidPassword(
        password: CharSequence
    ): ValidationErrorMessage? {
        return when {
            !StringUtils.isNotBlank(password) -> {
                ValidationErrorMessage.PASSWORD_BLANK_VALIDATION_ERROR
            }
            !passwordPattern.matcher(password).matches() -> {
                ValidationErrorMessage.PASSWORD_VALIDATION_ERROR
            }
            else -> null
        }
    }

    override fun isValidConfirmPassword(
        password: CharSequence,
        confirmPassword: CharSequence
    ): ValidationErrorMessage? {
        return when {
            !StringUtils.isNotBlank(confirmPassword) -> {
                ValidationErrorMessage.PASSWORD_BLANK_VALIDATION_ERROR
            }
            !passwordPattern.matcher(confirmPassword).matches()  -> {
                ValidationErrorMessage.PASSWORD_VALIDATION_ERROR
            }
            !StringUtils.equals(password, confirmPassword) -> {
                ValidationErrorMessage.PASSWORD_NOT_SAME_VALIDATION_ERROR
            }
            else -> null
        }
    }
}

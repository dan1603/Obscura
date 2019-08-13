package com.kalashnyk.denys.defaultproject.utils.validation

import android.util.Patterns
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
const val PASSWORD_PATTERN: String = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"

/**
 *
 */
val passwordPattern: Pattern = Pattern.compile(PASSWORD_PATTERN, Pattern.CASE_INSENSITIVE)

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
interface IValidator {

    /**
     * @param email
     */
    fun isValidEmail(
        email: CharSequence
    ): Boolean

    /**
     * @param phone
     */
    fun isValidPhone(
        phone: CharSequence
    ): Boolean

    /**
     * @param phone
     * @param codeCountry
     */
    fun isValidPhoneWithCodeCountry(
        phone: CharSequence,
        codeCountry: CharSequence
    ): Boolean

    /**
     * @param password
     */
    fun isValidPassword(
        password: CharSequence
    ): Boolean

    /**
     * @param password
     * @param confirmPassword
     */
    fun isValidConfirmPassword(
        password: CharSequence,
        confirmPassword: CharSequence
    ): Boolean

    /**
     * @param oldPassword
     * @param newPassword
     * @param confirmNewPassword
     */
    fun isValidResetPassword(
        oldPassword: CharSequence,
        newPassword: CharSequence,
        confirmNewPassword: CharSequence
    ): Boolean
}

class ValidatorImpl : IValidator {

    override fun isValidEmail(email: CharSequence): Boolean =
        StringUtils.isNotBlank(email) &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches()

    override fun isValidPhone(phone: CharSequence): Boolean =
        StringUtils.isNotBlank(phone) &&
                phone.length >= 11 &&
                Patterns.PHONE.matcher(phone).matches()

    override fun isValidPhoneWithCodeCountry(
        phone: CharSequence,
        codeCountry: CharSequence
    ): Boolean =
        isValidPhone(phone) &&
                phone.startsWith(codeCountry)

    override fun isValidPassword(password: CharSequence): Boolean =
        passwordPattern.matcher(password).matches()

    override fun isValidConfirmPassword(
        password: CharSequence,
        confirmPassword: CharSequence
    ): Boolean = isValidPassword(password) &&
            isValidPassword(confirmPassword) &&
            StringUtils.equals(password, confirmPassword)

    override fun isValidResetPassword(
        oldPassword: CharSequence,
        newPassword: CharSequence,
        confirmNewPassword: CharSequence
    ): Boolean = isValidPassword(oldPassword) &&
            isValidConfirmPassword(newPassword, confirmNewPassword) &&
            !StringUtils.equals(oldPassword, newPassword)
}

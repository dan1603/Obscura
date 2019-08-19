@file:JvmName("AuthFlowModelKt")

package com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow

import com.kalashnyk.denys.defaultproject.presentation.base.BaseChildModel

/**
 *
 */
const val typeChildField: String="typeChild"
/**
 *
 */
const val errorField: String="error"
/**
 *
 */
const val emailField: String="email"
/**
 *
 */
const val passwordField: String="password"
/**
 *
 */
const val passwordConfirmField: String="passwordConfirm"
/**
 *
 */
const val agreeTermsField: String="agreeTerms"

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class AuthFlowModel(type: IAuthFlow.AuthType) : BaseChildModel() {

    /**
     * @field typeChild
     */
    var typeChild: IAuthFlow.AuthType=type
        set(value) {
            field=value
            setChangedAndNotify("typeChild")
        }

    /**
     * @field emailField
     */
    var email: String = ""
        set(value) {
            field=value
            setChangedAndNotify("email")
        }

    /**
     * @field password
     */
    var password: String=""
        set(value) {
            field=value
            setChangedAndNotify("password")
        }

    /**
     * @field passwordConfirm
     */
    var passwordConfirm: String=""
        set(value) {
            field=value
            setChangedAndNotify("passwordConfirm")
        }

    /**
     * @field agreeTerms
     */
    var agreeTerms: Boolean=false
        set(value) {
            field=value
            setChangedAndNotify("agreeTerms")
        }

    /**
     * @field error
     */
    var error: AuthFlowErrorModel=AuthFlowErrorModel()
        set(value) {
            field=value
            setChangedAndNotify("error")
        }
}
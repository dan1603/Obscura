package com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow

import com.kalashnyk.denys.defaultproject.presentation.base.BaseChildModel

/**
 *
 */
class AuthChildCases(type: IAuthFlow.AuthType) : BaseChildModel() {

    /**
     *
     */
    var typeChild: IAuthFlow.AuthType = type
        set(value) {
            field = value
            setChangedAndNotify("typeChild")
        }

    var error: AuthFlowErrorModel = AuthFlowErrorModel()
        set(value) {
            field = value
            setChangedAndNotify("error")
        }
}
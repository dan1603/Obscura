package com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow

import com.kalashnyk.denys.defaultproject.presentation.base.BaseChildModel

class AuthChild(type: IAuthFlow.AuthType) : BaseChildModel() {

    /**
     *
     */
    var typeChild: IAuthFlow.AuthType = type
        set(value) {
            field = value
            setChangedAndNotify("typeChild")
        }

    var error: AuthFlowErrorModel? = null
        set(value) {
            field = value
            setChangedAndNotify("error")
        }
}
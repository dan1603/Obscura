package com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow

class AuthFlowModel(var type: IAuthFlow.AuthType,
                    var email : String? = "",
                    var password : String? = "",
                    var confirmPassword : String? = "",
                    var isAcceptTerms : Boolean? = false) {

}
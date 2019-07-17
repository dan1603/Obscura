package com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow

class AuthFlow(var type: IAuthFlow.AuthType,
               var email : String? = null,
               var password : String? = null,
               var passwordRepeat : String? = null) {

}
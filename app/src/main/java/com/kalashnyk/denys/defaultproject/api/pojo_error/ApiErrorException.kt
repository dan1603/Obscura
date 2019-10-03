package com.kalashnyk.denys.defaultproject.api.pojo_error

open class ApiErrorException(val apiError: ApiError) : Exception() {
    override fun getLocalizedMessage(): String =  apiError.errorMessage
}

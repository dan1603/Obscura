package com.kalashnyk.denys.moduleproject.remote_data_source.api.pojo_error

open class ApiErrorException(val apiError: ApiError) : Exception() {
    override fun getLocalizedMessage(): String =  apiError.errorMessage
}

package com.kalashnyk.denys.moduleproject.remote_data_source.api.pojo_error

import androidx.annotation.Keep
import com.kalashnyk.denys.moduleproject.remote_data_source.R

@Keep
data class ApiError(val code: Int, private val message: String?) {
    val errorMessage: String
        get()=message.orEmpty()
//        get() = if (message != null && message.isNotEmpty()) {
//            message
//        } else {
//            App.getContext().getString(R.string.error_please_try_again)
//        }
}

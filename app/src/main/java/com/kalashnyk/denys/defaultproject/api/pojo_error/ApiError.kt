package com.kalashnyk.denys.defaultproject.api.pojo_error

import androidx.annotation.Keep
import com.kalashnyk.denys.defaultproject.App
import com.kalashnyk.denys.defaultproject.R

@Keep
data class ApiError(val code: Int, private val message: String?) {
    val errorMessage: String
        get() = if (message != null && message.isNotEmpty()) {
            message
        } else {
            App.getContext().getString(R.string.error_please_try_again)
        }
}

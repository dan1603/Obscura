package com.kalashnyk.denys.defaultproject.api.pojo_error

import androidx.annotation.Keep

open class RequestError(val error: Throwable)

@Keep
class ConnectionError(error: Throwable) : RequestError(error)
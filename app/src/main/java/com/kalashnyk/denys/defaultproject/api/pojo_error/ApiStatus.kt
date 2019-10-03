package com.kalashnyk.denys.defaultproject.api.pojo_error

import androidx.annotation.Keep
import java.net.URL

@Keep
data class ApiStatus(val statusCode: Int, val url: URL)

package com.kalashnyk.denys.moduleproject.remote_data_source.api.pojo_error

import androidx.annotation.Keep
import java.net.URL

@Keep
data class ApiStatus(val statusCode: Int, val url: URL)

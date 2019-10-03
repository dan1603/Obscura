package com.kalashnyk.denys.defaultproject.api.interceptors

import com.kalashnyk.denys.defaultproject.utils.preference.PreferencesManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class IDHeadersInterceptor(preferencesManager: PreferencesManager) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original=chain.request()
        val builder=original.newBuilder()
        return chain.proceed(builder.build())
    }
}

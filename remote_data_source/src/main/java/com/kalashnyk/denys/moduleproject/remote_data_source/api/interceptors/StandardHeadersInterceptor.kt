package com.kalashnyk.denys.moduleproject.remote_data_source.api.interceptors

import android.os.Build
import androidx.annotation.RequiresApi
import com.kalashnyk.denys.moduleproject.utils.ApplicationUtils
import com.kalashnyk.denys.moduleproject.utils.*
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 *
 */
class StandardHeadersInterceptor(
    private val androidUtils: ApplicationUtils
) : Interceptor {

    /**
     *
     */
    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
            .header(HEADER_APP_VERSION, androidUtils.getAppVersion())
            .header(HEADER_APP_VERSION_HASH, androidUtils.ssh)
            .header(HEADER_BUILD_NUM, androidUtils.buildNumber)
            .header(HEADER_COUNTRY, androidUtils.country)
            .header(HEADER_CONTENT_TYPE, androidUtils.getNetworkType())
            .header(HEADER_CARRIER, androidUtils.getCarrier())
            .header(HEADER_OS_VERSION, androidUtils.getOsVersion())
            .header(HEADER_GOOGLE_ADVERTISER_ID, androidUtils.getAdId())
            .header(HEADER_DEVICE_NAME, androidUtils.model)
            .header(HEADER_DEVICE_TYPE, DEVICE_TYPE_VALUE) //2 denotes android
            .header(HEADER_TIMEZONE, androidUtils.timezone)
            .header(HEADER_ANDROID_ID, androidUtils.getDeviceId())
            .method(original.method(), original.body())

        return chain.proceed(builder.build())
    }
}

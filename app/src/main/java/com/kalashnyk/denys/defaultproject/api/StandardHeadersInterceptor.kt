package com.kalashnyk.denys.defaultproject.api

import android.os.Build
import androidx.annotation.RequiresApi
import com.kalashnyk.denys.defaultproject.utils.ApplicationConstants
import com.kalashnyk.denys.defaultproject.utils.ApplicationUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

/**
 *
 */
class StandardHeadersInterceptor @Inject constructor(
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
            .header(ApplicationConstants.HEADER_APP_VERSION, androidUtils.getAppVersion())
            .header(ApplicationConstants.HEADER_APP_VERSION_HASH, androidUtils.ssh)
            .header(ApplicationConstants.HEADER_BUILD_NUM, androidUtils.buildNumber)
            .header(ApplicationConstants.HEADER_COUNTRY, androidUtils.country)
            .header(ApplicationConstants.HEADER_CONTENT_TYPE, androidUtils.getNetworkType())
            .header(ApplicationConstants.HEADER_CARRIER, androidUtils.getCarrier())
            .header(ApplicationConstants.HEADER_OS_VERSION, androidUtils.getOsVersion())
            .header(ApplicationConstants.HEADER_GOOGLE_ADVERTISER_ID, androidUtils.getAdId())
            .header(ApplicationConstants.HEADER_DEVICE_NAME, androidUtils.model)
            .header(ApplicationConstants.HEADER_DEVICE_TYPE, ApplicationConstants.DEVICE_TYPE_VALUE) //2 denotes android
            .header(ApplicationConstants.HEADER_TIMEZONE, androidUtils.timezone)
            .header(ApplicationConstants.HEADER_ANDROID_ID, androidUtils.getDeviceId())
            .method(original.method(), original.body())

        return chain.proceed(builder.build())
    }
}

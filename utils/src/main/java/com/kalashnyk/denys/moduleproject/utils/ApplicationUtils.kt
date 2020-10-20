package com.kalashnyk.denys.moduleproject.utils

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Application
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Looper
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import java.io.IOException
import java.util.*

/**
 *
 */
class ApplicationUtils(
    /**
     *
     */
    val app: Application
) {

    /**
     *
     */
    val activeNetworkInfo: NetworkInfo?
        get()=(app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

    /**
     *
     */
    val language: String
        get()=Locale.getDefault().language

    /**
     *
     */
    val country: String
        get()=Locale.getDefault().country

    /**
     *
     */
    val timezone: String
        get()=TimeZone.getDefault().id

    /**
     *
     */
    val ssh: String
        get()=HashUtils.sha512(getAppVersion(), this)

    /**
     *
     */
    val model: String
        get()=Build.MODEL

    /**
     *
     */
    val buildNumber: String
        get()= Integer.toString(getBuildNumber())

    /**
     *
     */
    fun getAppId(): String {
        return app.packageName
    }

    /**
     *
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @SuppressLint("HardwareIds")
    fun getDeviceId(): String=
        Settings.Secure.getString(app.contentResolver, Settings.Secure.ANDROID_ID)

    /**
     *
     */
    fun getCarrier(): String=
        (app.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).simOperatorName //Do NOT need ACCESS_PHONE_STATE for this

    /**
     *
     */
    fun getOsVersion(): String=Integer.toString(Build.VERSION.SDK_INT)

    /**
     *
     */
    fun getAppVersion(): String {
        val pInfo: PackageInfo?
        return try {
            pInfo=app.packageManager.getPackageInfo(app.packageName, 0)
            val version=pInfo!!.versionName
            version
        } catch (e: PackageManager.NameNotFoundException) {
            "error_retrieving_version"
        }
    }

    /**
     *
     */
    fun getBuildNumber(): Int {
        val pInfo: PackageInfo?
        return try {
            pInfo=app.packageManager.getPackageInfo(app.packageName, 0)
            val version=pInfo!!.versionCode
            version
        } catch (e: PackageManager.NameNotFoundException) {
            -1
        }
    }

    /**
     * todo implement google Ad survey
     */
    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    @Throws(RuntimeException::class)
    fun getAdId(): String {
        if (Looper.getMainLooper().thread === Thread.currentThread())
            throw RuntimeException("You cannot call getAdId() from the UI thread! Use getAdIdInBackground() instead.")
        return try {
            val adInfo=AdvertisingIdClient.getAdvertisingIdInfo(app)
            if (adInfo != null)
                adInfo.id
            else
                "null"
        } catch (e: IOException) {
            "io_exception"
        } catch (e: GooglePlayServicesNotAvailableException) {
            "play_services_not_installed"
        } catch (e: GooglePlayServicesRepairableException) {
            "play_services_repairable_exception"
        }
    }

    /**
     *
     */
    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    fun getNetworkType(): String {
        return this.activeNetworkInfo?.run {
            when (type) {
                ConnectivityManager.TYPE_WIFI -> "NETWORK_TYPE_WIFI"
                ConnectivityManager.TYPE_MOBILE -> subtypeName
                else -> ""
            }
        } ?: return ""
    }
}

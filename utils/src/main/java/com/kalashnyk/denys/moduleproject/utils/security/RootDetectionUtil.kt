package com.kalashnyk.denys.moduleproject.utils.security

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import android.widget.Toast
import com.kalashnyk.denys.moduleproject.utils.BuildConfig
import com.kalashnyk.denys.moduleproject.utils.R

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

object RootDetectionUtil {

    val TEST_KEYS = "test-keys"
    val GENERIC = "generic"
    val UNKNOWN = "unknown"
    val GOOGLE_SDK = "google_sdk"
    val SDK = "sdk"
    val EMULATOR = "Emulator"
    val SDK_BUILT_FOR_X_86 = "Android SDK built for x86"
    val GENYMOTION = "Genymotion"
    val GOLDFISH = "goldfish"
    val RANCHU = "ranchu"
    val FRF_91 = "FRF91"
    val ANDROID_BUILD = "android-build"

    val suPaths = arrayOf(
        "/data/local/",
        "/data/local/bin/",
        "/data/local/xbin/",
        "/sbin/",
        "/su/bin/",
        "/system/bin/",
        "/system/bin/.ext/",
        "/system/bin/failsafe/",
        "/system/sd/xbin/",
        "/system/usr/we-need-root/",
        "/system/xbin/",
        "/cache",
        "/data",
        "/dev"
    )

    fun checkDeveloperModeOn(context: Context): Boolean {
        return 1 == Settings.Global.getInt(
            context.contentResolver,
            Settings.Global.ADB_ENABLED, 0
        )
    }

    fun checkTestKeys(): Boolean {
        val buildTags = android.os.Build.TAGS
        return buildTags != null && buildTags.contains(TEST_KEYS)
    }

    fun checkEmulatorBuildProps(): Boolean {
        return (Build.FINGERPRINT.startsWith(GENERIC)
                || Build.FINGERPRINT.startsWith(UNKNOWN)
                || Build.MODEL.contains(GOOGLE_SDK) || Build.MODEL.contains(SDK)
                || Build.MODEL.contains(EMULATOR)
                || Build.MODEL.contains(SDK_BUILT_FOR_X_86)
                || Build.MANUFACTURER.contains(GENYMOTION)
                || Build.BRAND.startsWith(GENERIC) && Build.DEVICE.startsWith(GENERIC)
                || Build.PRODUCT.contains(GOOGLE_SDK) || Build.PRODUCT.contains(SDK)
                || Build.HARDWARE.contains(GOLDFISH)
                || Build.HARDWARE.contains(RANCHU)
                || Build.BOARD.contains(UNKNOWN)
                || Build.ID.contains(FRF_91)
                || Build.MANUFACTURER.contains(UNKNOWN)
                || Build.SERIAL == null
                || Build.TAGS.contains(TEST_KEYS)
                || Build.USER.contains(ANDROID_BUILD))
    }

    private fun checkSuInPath(): Boolean {
        for (path in suPaths) {
            val completePath = path + "su"
            if (File(completePath).exists()) {
                return true
            }
        }
        return false
    }

    private fun executSu(): Boolean {
        var process: Process? = null
        return try {
            process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
            val reader = BufferedReader(InputStreamReader(process!!.inputStream))
            reader.readLine() != null
        } catch (t: Throwable) {
            false
        } finally {
            process?.destroy()
        }
    }

    fun confirmDeviceSecurity(context: Context): Boolean {
        var isDeviceInSecure = false
        if (!BuildConfig.DEBUG && (checkDeveloperModeOn(context) ||
                    checkSuInPath() || checkTestKeys())
        ) {
            isDeviceInSecure = true
        }
        return isDeviceInSecure
    }

    fun isSecureDevice(context: Context, alertDialog: AlertDialog): Boolean {
        if (confirmDeviceSecurity(context)) {
//            if (SessionManager.isUserLoggedIn(context)) {
//                Util.clearLocalDataLogout(context, false)
//                Timber.d("onActivityResumed: clearLocalDataLogout")
//            }
            val settingsIntent = Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS)
            val componentName = settingsIntent.resolveActivity(context.packageManager)
            if (componentName == null) {
                Toast.makeText(context, context.getString(R.string.root_detection_description), Toast.LENGTH_SHORT)
                    .show()
            } else {
                alertDialog.show()
            }
            return false
        }
        return true
    }
}
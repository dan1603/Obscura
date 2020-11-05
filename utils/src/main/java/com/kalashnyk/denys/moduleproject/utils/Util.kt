package com.kalashnyk.denys.moduleproject.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.AudioManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.StrictMode
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.core.graphics.ColorUtils
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView

import org.apache.commons.lang3.StringUtils

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URI
import java.net.URISyntaxException
import java.text.DecimalFormat
import java.util.Locale

import timber.log.Timber

object Util {

    val IO_BUFFER_SIZE = 8 * 1024
    val IMAGE_TYPE_GIF = ".gif"

    private val INTENT_ACTION = "android.intent.action.BADGE_COUNT_UPDATE"
    private val INTENT_EXTRA_BADGE_COUNT = "badge_count"
    private val INTENT_EXTRA_PACKAGENAME = "badge_count_package_name"
    private val INTENT_EXTRA_ACTIVITY_NAME = "badge_count_class_name"

    /**
     * Check if OS version is Marshmallow or higher
     */
    val isMarshmallowOrHigher: Boolean
        get() = android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    /**
     * Check if OS version is Lollipop or higher
     */
    val isLollipopOrHigher: Boolean
        get() = android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

    /**
     * Check if OS version is nougat or higher
     */
    val isNougatOrHigher: Boolean
        get() = android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

    /**
     * Check if OS version is Oreo or higher
     */
    val isOreoOrHigher: Boolean
        get() = android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

    fun getUriFromFile(context: Context, file: File?): Uri? {
        return if (file == null)
            null
        else
            FileProvider.getUriForFile(
                context,
                context.resources.getString(
                    R.string.file_provider_authority,
                    BuildConfig.LIBRARY_PACKAGE_NAME
                ),
                file
            )
    }

    fun getActivityFromView(view: View): Activity? {
        var context = view.context
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context as Activity
            }
            context = (context as ContextWrapper).baseContext
        }
        return null
    }

    fun isLink2AnimatedImage(link: String): Boolean {
        return !StringUtils.isEmpty(link) && link.contains(IMAGE_TYPE_GIF)
    }

    fun turnOffPlayingOutside(context: Context) {
        val mAudioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        if (mAudioManager.isMusicActive) {

            val result = mAudioManager.requestAudioFocus(
                { }, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
            )

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                Timber.d("Granted audio permission")
            }
        }
    }

    fun releasePlayingOutside(context: Context) {
        (context
            .getSystemService(Context.AUDIO_SERVICE) as AudioManager).abandonAudioFocus { }
    }

    fun setDisableDeathOnFileUriExposure() {
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                val m = StrictMode::class.java
                    .getMethod("disableDeathOnFileUriExposure")
                m.invoke(null)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun disableDeathOnFileUriExposureViaVmPolicy() {
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }

    fun setBackgroundImagePath(
        context: Context,
        drawable: Drawable?, feedItemId: String
    ): String? {
        if (drawable == null) {
            return null
        } else {
            var out: FileOutputStream? = null
            val mBitmap = drawableToBitmap(drawable)

            if (mBitmap != null) {
                try {
                    val file = createImageTempFile(context, feedItemId)
                    out = FileOutputStream(file)
                    mBitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                    out.flush()
                    out.close()
                    return "file://" + file.path
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    try {
                        if (out != null) {
                            out.close()
                        }
                    } catch (e1: IOException) {
                        e1.printStackTrace()
                    }

                }
            }

            return null
        }
    }

    fun createImageTempFile(context: Context, feedItemId: String): File {
        val dir = File(
            (context.getExternalFilesDir(null)!!).toString() + "/sc/temp_share_image"
        )
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return File(dir, "share_image_$feedItemId.png")
    }

//    fun createVideoTempFile(context: Context, fileName: String): File {
//        val dir = File(
//            (context.getExternalFilesDir(null)!!).toString() + "/sc/temp_share_video"
//        )
//        if (!dir.exists()) {
//            dir.mkdirs()
//        }
//        return File(
//            getTempDirrectory(context, SubmitContentType.VIDEO),
//            fileName
//        )
//    }

//    fun getTempDirrectory(
//        context: Context,
//        contentType: SubmitContentType
//    ): File {
//        var dirName = ""
//        when (contentType) {
//            VIDEO -> dirName = "temp_share_video"
//            IMAGE -> dirName = "temp_share_image"
//        }
//        val dir = File(
//            (context.getExternalFilesDir(null)).toString() + "/sc/" + dirName
//        )
//        if (!dir.exists()) {
//            dir.mkdirs()
//        }
//        return dir
//    }

    fun isFileInTempDir(context: Context, file: String): Boolean {
        return StringUtils.contains(
            file,
            context.getExternalFilesDir(null)!!.path
        ) || StringUtils.contains(file, getCacheDir(context).path)
    }

    fun isValidUrl(url: String?): Boolean {
        return url != null && (url!!.toLowerCase(Locale.US).startsWith("http") || url!!.toLowerCase(Locale.US).startsWith(
            "https"
        ))
    }

//    @JvmOverloads
//    fun clearLocalDataLogout(
//        context: Context,
//        launchlauncher: Boolean = true
//    ) {
//        CookieSyncManager.createInstance(context)
//        CookieManager.getAppContext().removeAllCookie()
//
//        App.getAppContext().clearDbData()
//        if (launchlauncher) {
//            launchLauncher(context)
//        }
//    }

//    fun handleErrorResponse(
//        context: Context,
//        errorResponse: ErrorResponse
//    ) {
//
//        for (error in errorResponse.getErrors()) {
//
//            if (error.getCode().equals("member.unauthorized")) {
//                Timber.e("Response error code: %s", error.getCode())
////                StateManager.setSessionId(context, null)
//            }
//
//            if (error.getCode().equals("member.forbidden")) {
//                Timber.e("Response error code: %s", error.getCode())
//            }
//        }
//    }


    fun isConnectedToNetwork(
        context: Context,
        alertDialog: AlertDialog? = null
    ): Boolean {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork!!.isConnected

        if (!isConnected) {
            alertDialog?.show()
            return false
        }

        return true
    }

    fun getAppVersion(context: Context): String {
        var version = ""
        try {
            val pInfo = context.packageManager
                .getPackageInfo(context.packageName, 0)
            version = pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return version
    }

    fun getCacheDir(context: Context): File {
        val cacheDir: File
        if (android.os.Environment.getExternalStorageState() == android.os.Environment.MEDIA_MOUNTED) {
            // The device has a SD card
            cacheDir = File(
                android.os.Environment.getExternalStorageDirectory(),
                ".hivecache"
            )
        } else {
            cacheDir = context.cacheDir
        }

        if (!cacheDir.exists()) {
            cacheDir.mkdirs()
        }

        return cacheDir
    }

    fun saveBitmapToFile(bitmap: Bitmap, file: File): File? {
        var out: FileOutputStream? = null

        try {
            out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.flush()
            out.close()
            return file
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                if (out != null) {
                    out.close()
                }
            } catch (e1: IOException) {
                e1.printStackTrace()
            }

        }

        return null
    }

    fun deleteFile(context: Context, photoUri: Uri): Int {
        try {
            return context.contentResolver
                .delete(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    MediaStore.MediaColumns.DATA + "='"
                            + photoUri.path + "'", null
                )
        } catch (e: Exception) {
            Timber.e(e)
            return 0
        }

    }

    fun deleteIfTemp(context: Context, contentUri: Uri?) {
        if ((contentUri != null && isFileInTempDir(
                context, contentUri.toString()
            ))
        ) {
            if (deleteFile(context, contentUri) <= 0) {
                contentUri.path?.let {
                    FileUtil.deleteFile(
                        FileUtil.resolveFilePath(it)
                    )
                }
            }
        }
    }

    fun clearAppBadge(context: Context) {
        if (Build.MANUFACTURER == "samsung") {
            val componentName = context.packageManager
                .getLaunchIntentForPackage(context.packageName)!!
                .component
            val intent = Intent(INTENT_ACTION)
            intent.putExtra(INTENT_EXTRA_BADGE_COUNT, 0)
            intent.putExtra(
                INTENT_EXTRA_PACKAGENAME,
                componentName!!.packageName
            )
            intent.putExtra(
                INTENT_EXTRA_ACTIVITY_NAME,
                componentName.className
            )
            if (canResolveBroadcast(context, intent)) {
                context.sendBroadcast(intent)
            } else {
                Timber.d("Cannot clear badge")
            }
        }
    }

    fun canResolveBroadcast(context: Context, intent: Intent): Boolean {
        val packageManager = context.packageManager
        val receivers = packageManager
            .queryBroadcastReceivers(intent, 0)
        return receivers != null && receivers!!.size > 0
    }

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    fun renderHtmlTextView(textView: TextView, html: String, colorPrimary : Int) {
        if (StringUtils.isNotBlank(html)) {
            textView.setTextColor(
                ColorUtils.setAlphaComponent(
                    colorPrimary,
                    (COLOR_ALPHA_255 * 0.6) as Int
                )
            )
            textView.setLinkTextColor(
                ColorUtils.setAlphaComponent(
                    colorPrimary,
                    (COLOR_ALPHA_255 * 0.6) as Int
                )
            )
            if (isNougatOrHigher) {
                textView.text = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
            } else {
                textView.text = Html.fromHtml(html)
            }
            textView.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    fun readableFileSize(size: Long): String {
        if (size <= 0)
            return "0"
        val units = arrayOf("B", "kB", "MB", "GB", "TB")
        val digitGroups = (Math.log10(size.toDouble()) / Math.log10(1024.0)).toInt()
        return (DecimalFormat("#,##0.#").format(
            size / Math.pow(1024.0, digitGroups.toDouble())
        ) + " " + units[digitGroups])
    }

    fun URIFromString(link: String): URI? {
        try {
            return URI(link)
        } catch (e: NullPointerException) {
            return null
        } catch (e: URISyntaxException) {
            return null
        }

    }


//    fun checkPlayServices(activity: Activity): Boolean {
//        if (!StateManager.getRegisteredPushNotification(activity)) {
//            val apiAvailability = GoogleApiAvailability
//                .getAppContext()
//            val resultCode = apiAvailability
//                .isGooglePlayServicesAvailable(activity)
//
//            val b = BehaviorAnalytics.builder().build()
//
//            if (resultCode != ConnectionResult.SUCCESS) {
//                val playServicesDialog = apiAvailability.getErrorDialog(activity, resultCode, 2222)
//                playServicesDialog.setCancelable(false)
//
//                if (apiAvailability.isUserResolvableError(resultCode)) {
//                    if (playServicesDialog is AlertDialog) {
//                        (playServicesDialog as AlertDialog).setButton(
//                            DialogInterface.BUTTON_NEGATIVE,
//                            activity.getString(R.string.not_now)
//                        ) { dialog1, which ->
//                            dialog1.dismiss()
//                            StateManager
//                                .setRegisteredPushNotification(
//                                    activity, true
//                                )
//                            b.track(BehaviorAnalytics.PLAY_SERVICES_NOT_NOW)
//                        }
//                        (playServicesDialog as AlertDialog).setButton(
//                            DialogInterface.BUTTON_POSITIVE,
//                            activity.getString(R.string.install)
//                        ) { dialog1, which ->
//                            val intent = Intent()
//                            val pendingIntent = GoogleApiAvailability
//                                .getAppContext()
//                                .getErrorResolutionPendingIntent(
//                                    activity,
//                                    resultCode, 0
//                                )
//
//                            b.track(BehaviorAnalytics.PLAY_SERVICES_INSTALL)
//
//                            try {
//                                pendingIntent.send(
//                                    activity,
//                                    0, intent
//                                )
//                                StateManager
//                                    .setRegisteredPushNotification(
//                                        activity,
//                                        false
//                                    )
//                            } catch (e: Exception) {
//                                Timber.e(
//                                    "Could not find Google Play Services on this device"
//                                )
//                            }
//                        }
//                    }
//                }
//                playServicesDialog.show()
//                b.track(BehaviorAnalytics.PLAY_SERVICES_LOAD)
//                Timber.d("Error. GooglePlayServices are not available")
//                return false
//            }
//            return true
//        }
//        return false
//    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap? {
        return if ((drawable.intrinsicWidth > 0 && drawable.intrinsicHeight > 0)) {
            Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
            )
        } else null
    }

//    private fun launchLauncher(context: Context) {
//        val intent = Intent(context, LauncherActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        intent.putExtra(LauncherActivity.EXTRA_CLEAR_PREFERENCES, true)
//        context.startActivity(intent)
//    }

    fun getDrawableWithText(context: Context, text: String, textSize: Float, padding: Int, colorAccent : Int): Drawable {
        val textPaint = Paint()
        textPaint.color = Color.WHITE
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = textSize
        textPaint.textAlign = Paint.Align.CENTER

        val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        bgPaint.color = colorAccent

        val tw = textPaint.measureText(text) + 2 * padding
        val h = textSize + 2 * padding
        val w = Math.max(tw, textSize)

        val rectF = RectF(0f, 0f, w, h)

        val bitmap = Bitmap.createBitmap(w.toInt(), h.toInt(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawRoundRect(rectF, w / 8, h / 8, bgPaint)
        canvas.drawText(text, w / 2, (h - 2 * padding), textPaint)

        return BitmapDrawable(
            context.resources, bitmap
        )
    }
}

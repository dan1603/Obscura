package com.kalashnyk.denys.defaultproject.utils

import android.annotation.TargetApi
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Outline
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ColorInt
import androidx.appcompat.app.AlertDialog
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.presentation.activities.main.MainActivity
import com.kalashnyk.denys.defaultproject.presentation.navigation.INavigation

object UIUtil {

    fun getScreenWidthInPixels(context: Context): Float {
        val wm = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val dm = DisplayMetrics()
        display.getMetrics(dm)
        return dm.widthPixels.toFloat()
    }

    fun getScreenHeightInPixels(context: Context): Float {
        val wm = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val dm = DisplayMetrics()
        display.getMetrics(dm)
        return dm.heightPixels.toFloat()
    }

    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources
            .getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun setFitsSystemWindows(view: View?, fitSystemWindows: Boolean, applyToChildren: Boolean) {
        if (view == null) return
        view.fitsSystemWindows = fitSystemWindows
        if (applyToChildren && view is ViewGroup) {
            val viewGroup = view as ViewGroup?
            var i = 0
            val n = viewGroup!!.childCount
            while (i < n) {
                viewGroup.getChildAt(i).fitsSystemWindows = fitSystemWindows
                i++
            }
        }
    }

    fun setMarginTop(view: View, margin: Int) {
        val params = view.layoutParams
        if (params is ViewGroup.MarginLayoutParams) {
            params.setMargins(0, margin, 0, 0)
        }
    }


    fun convertDpToPixel(dp: Float, context: Context): Int {
        val resources = context.resources
        val metrics = resources.displayMetrics
        val px = dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        return px.toInt()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun tintDrawable(drawable: Drawable, color: Int): Drawable {
        if (Util.isMarshmallowOrHigher) {
            drawable.setTint(color)
        } else {
            drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
        return drawable
    }

    fun hideKeyboard(activity: Activity?) {
        if (activity != null && !activity.isFinishing) {
            val imm = activity
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (activity.window != null
                && activity.window.currentFocus != null
                && activity.window.currentFocus!!
                    .windowToken != null
            ) {
                imm.hideSoftInputFromWindow(
                    activity.window.currentFocus!!.windowToken,
                    0
                )
            }
        }
    }

    fun hideKeyboard(context: Context) {
        val imm = context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null && imm.isActive) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }
    }

    fun hideKeyboard(view: View) {
        val imm = view.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard(activity: Activity?, view: View) {
        if (activity != null && !activity.isFinishing) {
            view.requestFocus()
            view.requestFocusFromTouch()
            (activity
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .showSoftInput(
                    view,
                    InputMethodManager.SHOW_IMPLICIT
                )
        }
    }

    fun setSelection(editText: EditText) {
        val length = editText.text.toString().length
        if (length > 0) {
            editText.postDelayed({
                if (length == editText.text.toString().length) {
                    editText.setSelection(length)
                }
            }, 50)
        }
    }

    fun manipulateColor(color: Int, factor: Float): Int {
        val a = Color.alpha(color)
        val r = Math.round(Color.red(color) * factor)
        val g = Math.round(Color.green(color) * factor)
        val b = Math.round(Color.blue(color) * factor)
        return Color.argb(
            a,
            Math.min(r, 255),
            Math.min(g, 255),
            Math.min(b, 255)
        )
    }

    fun showOfflineDialog(
        activity: Activity?,
        finishActivity: Boolean
    ) {
        if (activity == null) {
            return
        }

        val alert = AlertDialog.Builder(activity, R.style.AlertDialogTheme)
            .setMessage(activity.getString(R.string.network_offline))
            .setCancelable(false).setPositiveButton(R.string.ok,
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    if (finishActivity && !activity.isFinishing) {
                        activity.finish()
                    }
                })
            .create()

        if (!activity.isFinishing) {
            alert.show()
        }
    }

    fun setStatusBarImmersiveMode(win: Window, @ColorInt color: Int) {
        win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        win.attributes.systemUiVisibility =
            win.attributes.systemUiVisibility or (View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            win.statusBarColor = color
        }

        setStatusBar(win, StatusBarMode.TRANSPARENT)
    }

    fun setStatusBar(window: Window, mode: StatusBarMode) {
        try {
            val layoutParamsClass = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val tranceFlag = layoutParamsClass.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT").getInt(null)
            val darkModeFlag = layoutParamsClass.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(null)

            val extraFlagsField = Window::class.java.getMethod(
                "setExtraFlags",
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType
            )
            when (mode) {
                UIUtil.StatusBarMode.TRANSPARENT -> extraFlagsField.invoke(window, tranceFlag, tranceFlag)
                UIUtil.StatusBarMode.TRANSPARENT_DARK_TEXT -> extraFlagsField.invoke(
                    window,
                    tranceFlag or darkModeFlag,
                    tranceFlag or darkModeFlag
                )
            }
        } catch (ignored: Exception) {
        }

    }

    enum class StatusBarMode {
        TRANSPARENT, TRANSPARENT_DARK_TEXT
    }

    @TargetApi(21)
    class ShadowOutline(internal var width: Int, internal var height: Int) : ViewOutlineProvider() {

        override fun getOutline(view: View, outline: Outline) {
            outline.setRect(0, 0, width, height)
        }
    }

    fun handleBackButton(navigator : INavigation) {
        if (Util.runningActivityCount < 2) {
            navigator.openMainScreen()
        }
    }
}

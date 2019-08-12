package com.kalashnyk.denys.defaultproject.utils.extention

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.kalashnyk.denys.defaultproject.R
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 *
 */
fun AppCompatActivity.hideKeyboard() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    val windowToken = currentFocus?.windowToken
    if (windowToken != null) {
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }
}

/**
 * @param key
 * @param def
 * @return
 */
fun AppCompatActivity.extraString(key: String? = null, def: String? = null) =
        delegate(key, def, { keyValue, defValue ->
            intent?.extras?.getString(keyValue, defValue)
        }, { keyValue, value ->
            intent?.putExtra(keyValue, value)
        })

/**
 * @param key
 * @param def
 * @return
 */
fun AppCompatActivity.extraInt(key: String? = null, def: Int = 0) =
        delegate(key, def, { keyValue, defValue ->
            intent?.extras?.getInt(keyValue, defValue)
        }, { keyValue, value ->
            intent?.putExtra(keyValue, value)
        })

/**
 * @param key
 * @param def
 * @return
 */
fun AppCompatActivity.extraBoolean(key: String? = null, def: Boolean = false) =
        nonNullDelegate(key, def, { keyValue, defValue ->
            intent?.extras?.getBoolean(keyValue, defValue) ?: defValue
        }, { keyValue, value ->
            intent?.putExtra(keyValue, value)
        })

/**
 * @param key
 * @param def
 * @return
 */
fun <T: Enum<*>> AppCompatActivity.extraEnum(key: String? = null, def: T) =
        nonNullDelegate(key, def, { keyValue, defValue ->
            (intent?.extras?.getSerializable(keyValue) as? T) ?: defValue
        }, { keyValue, value ->
            intent?.putExtra(keyValue, value)
        })

/**
 * @param key
 * @param def
 * @return
 */
fun <T: Parcelable> AppCompatActivity.extraParcelable(key: String? = null, def: T? = null) =
        delegate(key, def, { keyValue, defValue ->
            intent?.extras?.getParcelable(keyValue) ?: defValue
        }, { keyValue, value ->
            intent?.putExtra(keyValue, value)
        })

/**
 * @param key
 * @param def
 * @return
 */
fun <T: Serializable> AppCompatActivity.extraSerializable(key: String? = null, def: T? = null) =
        delegate(key, def, { keyValue, defValue ->
            (intent?.extras?.getSerializable(keyValue) as? T) ?: defValue
        }, { keyValue, value ->
            intent?.putExtra(keyValue, value)
        })

/**
 * @param params
 * @return
 * @return
 */
fun Intent.withArguments(vararg params: Pair<String, Any?>): Intent {
    for ((key, value) in params) {
        if (value != null) {
            when (value) {
                is Boolean -> putExtra(key, value)
                is Byte -> putExtra(key, value)
                is Char -> putExtra(key, value)
                is Short -> putExtra(key, value)
                is Int -> putExtra(key, value)
                is Long -> putExtra(key, value)
                is Float -> putExtra(key, value)
                is Double -> putExtra(key, value)
                is String -> putExtra(key, value)
                is CharSequence -> putExtra(key, value)
                is Parcelable -> putExtra(key, value)
                is Serializable -> putExtra(key, value)
                is BooleanArray -> putExtra(key, value)
                is ByteArray -> putExtra(key, value)
                is CharArray -> putExtra(key, value)
                is DoubleArray -> putExtra(key, value)
                is FloatArray -> putExtra(key, value)
                is IntArray -> putExtra(key, value)
                is LongArray -> putExtra(key, value)
                is Array<*> -> {
                    @Suppress("UNCHECKED_CAST")
                    when {
                        value.isArrayOf<Parcelable>() -> putExtra(key, value as Array<out Parcelable>)
                        value.isArrayOf<CharSequence>() -> putExtra(key, value as Array<out CharSequence>)
                        value.isArrayOf<String>() -> putExtra(key, value as Array<out String>)
                        else -> throw IllegalArgumentException("Unsupported bundle component (${value::class})")
                    }
                }
                is ShortArray -> putExtra(key, value)
                is Bundle -> putExtra(key, value)
                null -> {/* ignore */}
                else -> throw IllegalArgumentException("Unsupported bundle component (${value::class})")
            }
        }
    }
    return this
}


/**
 * @return
 */
fun AppCompatActivity.getDisplayPoint(): Point {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    val outPoint = Point()
    display.getSize(outPoint)
    return outPoint
}

/**
 * @param container
 * @param fragment
 */
fun AppCompatActivity.replaceFragment(
    container: Int,
    fragment: Fragment
) = this.replaceFragment(container, fragment, false, false)

/**
 * @param container
 * @param fragment
 * @param addToBackStack
 * @param moveOnRight
 */
fun AppCompatActivity.replaceFragment(
    container: Int,
    fragment: Fragment,
    addToBackStack: Boolean,
    moveOnRight: Boolean
) = this.replaceFragment(container, fragment, addToBackStack, true, moveOnRight)

/**
 * @param container
 * @param fragment
 * @param addToBackStack
 * @param needAnimate
 * @param moveOnRight
 */
fun AppCompatActivity.replaceFragment(
    container: Int,
    fragment: Fragment,
    addToBackStack: Boolean,
    needAnimate: Boolean,
    moveOnRight: Boolean
) = this.supportFragmentManager.replaceFragment(container, fragment, addToBackStack, needAnimate, moveOnRight)

/**
 * @param text
 */
fun AppCompatActivity.showToast(text: Any) = Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT).show()

/**
 * @param text
 */
fun AppCompatActivity.showSnack(text: String) = Snackbar.make(this.findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG).show()

/**
 * @param message
 * @param length
 */
fun View.snack(message: String, length: Int = Snackbar.LENGTH_SHORT) = Snackbar.make(this, message, length).show()

/**
 * @param message
 * @param length
 */
inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_SHORT, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}

/**
 * @param action
 * @param color
 * @param listener
 */
fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}

private inline fun <T> AppCompatActivity.delegate(
    key: String?,
    defaultValue: T,
    crossinline getter: AppCompatActivity.(String, T) -> T?,
    crossinline setter: AppCompatActivity.(String, T?) -> Unit
): ReadWriteProperty<Any, T?> {
    return object : ReadWriteProperty<Any, T?> {
        override fun getValue(thisRef: Any, property: KProperty<*>) =
            getter(key ?: property.name, defaultValue)

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) =
            setter(key ?: property.name, value)
    }
}

private inline fun <T> AppCompatActivity.nonNullDelegate(
    key: String?,
    defaultValue: T,
    crossinline getter: AppCompatActivity.(String, T) -> T,
    crossinline setter: AppCompatActivity.(String, T?) -> Unit
): ReadWriteProperty<Any, T> {
    return object : ReadWriteProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>) =
            getter(key ?: property.name, defaultValue) ?: defaultValue

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) =
            setter(key ?: property.name, value)
    }
}

private fun FragmentManager.replaceFragment(
    containerViewId: Int,
    fragment: Fragment,
    addToBackStack: Boolean,
    needAnimate: Boolean,
    moveOnRight : Boolean) {
    var fragmentTransaction = this.beginTransaction()
    val fragmentTag = fragment.javaClass.simpleName
    if (addToBackStack) fragmentTransaction = fragmentTransaction.addToBackStack(fragmentTag)
    if (needAnimate) {
        val enterAnimation = if (moveOnRight) R.animator.slide_in_left else R.animator.pop_out_right
        val exitAnimation = if (moveOnRight) R.animator.slide_out_right else R.animator.pop_in_left
        val popEnterAnimation = if (moveOnRight) R.animator.pop_out_right else R.animator.slide_in_left
        val popExitAnimation = if (moveOnRight) R.animator.pop_in_left else R.animator.slide_out_right
        fragmentTransaction.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
    }
    fragmentTransaction.replace(containerViewId, fragment, fragmentTag).commit()
}

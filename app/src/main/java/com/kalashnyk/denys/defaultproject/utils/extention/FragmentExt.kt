package com.kalashnyk.denys.defaultproject.utils.extention

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment
import com.kalashnyk.denys.moduleproject.utils.AppLog
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun Fragment.fragmentArgInt(key: String? = null, def: Int = 0) =
        delegate(key, def, { keyValue, defValue ->
            arguments?.getInt(keyValue, def) ?: defValue
        }, { keyValue, value ->
            arguments?.putInt(keyValue, value ?: def)
        })

fun Fragment.fragmentArgString(key: String? = null, def: String? = null) =
        delegate(key, def, { keyValue, defValue ->
            arguments?.getString(keyValue, defValue) ?: defValue
        }, { keyValue, value ->
            arguments?.putString(keyValue, value)
        })

fun <T : Parcelable> Fragment.fragmentArgParcelable(key: String? = null, def: T? = null) =
        delegate(key, def, { keyValue, defValue ->
            arguments?.getParcelable(keyValue) ?: defValue
        }, { keyValue, value ->
            arguments?.putParcelable(keyValue, value)
        })

fun <T : Serializable> Fragment.fragmentArgSerializable(key: String? = null, def: T? = null) =
        delegate(key, def, { keyValue, defValue ->
            arguments?.getSerializable(keyValue) as T? ?: defValue
        }, { keyValue, value ->
            arguments?.putSerializable(keyValue, value)
        })


fun Fragment.fragmentArgLong(key: String? = null, def: Long = 0) =
        delegate(key, def, { keyValue, defValue ->
            arguments?.getLong(keyValue) ?: defValue
        }, { keyValue, value ->
            arguments?.putLong(keyValue, value ?: def)
        })

fun Fragment.fragmentArgBoolean(key: String? = null, def: Boolean = false) =
        delegate(key, def, { keyValue, defValue ->
            arguments?.getBoolean(keyValue) ?: defValue
        }, { keyValue, value ->
            arguments?.putBoolean(keyValue, value ?: def)
        })

private inline fun <T> Fragment.delegate(
        key: String?,
        defaultValue: T,
        crossinline getter: Fragment.(String, T) -> T?,
        crossinline setter: Fragment.(String, T?) -> Unit
): ReadWriteProperty<Any, T?> {
    return object : ReadWriteProperty<Any, T?> {
        override fun getValue(thisRef: Any, property: KProperty<*>) =
                getter(key ?: property.name, defaultValue)

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) =
                setter(key ?: property.name, value)
    }
}

@Suppress("ConstantConditionIf")
fun BaseFragment<*>.log(message : String) {
    val activity = this.activity
    if (activity is BaseActivity<*> && activity.isDebugEnabled()) {
        AppLog.d("* $message")
    }
}

fun <T: Fragment> T.withArguments(vararg params: Pair<String, Any?>): T {
    arguments = bundleOf(*params)
    return this
}

fun bundleOf(vararg params: Pair<String, Any?>): Bundle {
    val bundle = Bundle()
    for ((key, value) in params) {
        when (value) {
            is Boolean -> bundle.putBoolean(key, value)
            is Byte -> bundle.putByte(key, value)
            is Char -> bundle.putChar(key, value)
            is Short -> bundle.putShort(key, value)
            is Int -> bundle.putInt(key, value)
            is Long -> bundle.putLong(key, value)
            is Float -> bundle.putFloat(key, value)
            is Double -> bundle.putDouble(key, value)
            is String -> bundle.putString(key, value)
            is CharSequence -> bundle.putCharSequence(key, value)
            is Parcelable -> bundle.putParcelable(key, value)
            is Serializable -> bundle.putSerializable(key, value)
            is BooleanArray -> bundle.putBooleanArray(key, value)
            is ByteArray -> bundle.putByteArray(key, value)
            is CharArray -> bundle.putCharArray(key, value)
            is DoubleArray -> bundle.putDoubleArray(key, value)
            is FloatArray -> bundle.putFloatArray(key, value)
            is IntArray -> bundle.putIntArray(key, value)
            is LongArray -> bundle.putLongArray(key, value)
            is Array<*> -> {
                @Suppress("UNCHECKED_CAST")
                when {
                    value.isArrayOf<Parcelable>() -> bundle.putParcelableArray(key, value as Array<out Parcelable>)
                    value.isArrayOf<CharSequence>() -> bundle.putCharSequenceArray(key, value as Array<out CharSequence>)
                    value.isArrayOf<String>() -> bundle.putStringArray(key, value as Array<out String>)
                    else -> throw IllegalArgumentException("Unsupported bundle component (${value::class})")
                }
            }
            is ShortArray -> bundle.putShortArray(key, value)
            is Bundle -> bundle.putBundle(key, value)
            null -> {/* ignore */}
            else -> throw IllegalArgumentException("Unsupported bundle component (${value::class})")
        }
    }
    return bundle
}
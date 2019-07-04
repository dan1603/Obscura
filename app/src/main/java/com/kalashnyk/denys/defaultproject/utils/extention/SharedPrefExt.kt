package com.kalashnyk.denys.defaultproject.utils.extention

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <reified T> Gson.fromGson(json: String) = this.fromJson<T>(json, object : TypeToken<T>(){}.type)

private inline fun <T> SharedPreferences.delegate(
        defaultValue: T,
        key: String?,
        crossinline getter: SharedPreferences.(String, T) -> T,
        crossinline setter: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor
): ReadWriteProperty<Any, T> {
    return object : ReadWriteProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>) =
                getter(key ?: property.name, defaultValue)

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) =
                edit().setter(key ?: property.name, value).apply()
    }
}

fun SharedPreferences.int(def: Int = 0, key: String? = null) =
        delegate(def, key, SharedPreferences::getInt, SharedPreferences.Editor::putInt)

fun SharedPreferences.long(def: Long = 0, key: String? = null) =
        delegate(def, key, SharedPreferences::getLong, SharedPreferences.Editor::putLong)

fun SharedPreferences.boolean(def: Boolean = false, key: String? = null) =
        delegate(def, key, SharedPreferences::getBoolean, SharedPreferences.Editor::putBoolean)

fun SharedPreferences.string(def: String? = null, key: String? = null, beforeSetter: (String?) -> Unit = {}) =
        delegate(def, key, SharedPreferences::getString) { setKey: String, setValue: String? ->
            beforeSetter(setValue)
            putString(setKey, setValue)
        }

fun SharedPreferences.floatList(def: MutableList<Float>? = null, key: String? = null, gson: Gson = Gson()) =
        delegate(def, key,
                { prefKey, defValue ->
                    gson.fromJson(getString(prefKey, null), object : TypeToken<MutableList<Float>>() {}.type) ?: defValue
                },
                { prefKey, value ->
                    putString(prefKey, gson.toJson(value))
                })

fun <T> SharedPreferences.gson(tClass: Class<T>, def: T? = null, gson: Gson = Gson(), key: String? = null) =
        delegate(def, key,
                { prefKey, defValue ->
                    gson.fromJson(getString(prefKey, null), tClass) ?: defValue
                },
                { prefKey, value ->
                    putString(prefKey, gson.toJson(value))
                })

fun <T> SharedPreferences.mutableList(def: MutableList<T>? = null, gson: Gson = Gson(), key: String? = null) =
        delegate(def, key,
                { prefKey, defValue ->
                    gson.fromJson(getString(prefKey, null), object : TypeToken<MutableList<T>>() {}.type) ?: defValue
                },
                { prefKey, value ->
                    putString(prefKey, gson.toJson(value))
                })

fun SharedPreferences.clear() = this.edit().clear().apply()
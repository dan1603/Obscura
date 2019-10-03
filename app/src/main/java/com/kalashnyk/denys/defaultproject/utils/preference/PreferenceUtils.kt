package com.kalashnyk.denys.defaultproject.utils.preference

import android.content.SharedPreferences

//todo using extensions

open class PreferenceUtils (private val sharedPreferences: SharedPreferences) {

    protected fun getInt(key: String, default: Int = 0): Int = sharedPreferences.getInt(key, default)

    protected fun putInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    protected fun getLong(key: String): Long = sharedPreferences.getLong(key, 0)

    protected fun putLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    protected fun getFloat(key: String): Float = sharedPreferences.getFloat(key, 0f)

    protected fun putFloat(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    protected fun getString(key: String): String = sharedPreferences.getString(key, "")

    protected fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    protected fun putStringNow(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).commit()
    }

    protected fun getBoolean(key: String, default: Boolean = false): Boolean =
        sharedPreferences.getBoolean(key, default)

    protected fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    protected open fun clearRepository() {
        sharedPreferences.edit().clear().apply()
    }
}

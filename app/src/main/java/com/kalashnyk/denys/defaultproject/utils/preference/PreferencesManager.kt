package com.kalashnyk.denys.defaultproject.utils.preference

import android.content.SharedPreferences

private const val USER_ID_KEY="user_id"
private const val TOKEN_KEY="token"
private const val LAST_SAVED_VERSION_CODE="last_version_code"
private const val USER_GOOGLE_ID="user_google_id"

class PreferencesManager(sharedPreferences: SharedPreferences) : PreferenceUtils(sharedPreferences) {

    open var token: String
        get()=getString(TOKEN_KEY)
        set(value) {
            putString(TOKEN_KEY, value)
        }

    open var userGoogleToken: String
        get()=getString(USER_GOOGLE_ID)
        set(value) {
            putString(USER_GOOGLE_ID, value)
        }

    open var hasToken: Boolean=false
        get()=getString(TOKEN_KEY).isNotBlank()

    open var lastSavedAppVersion: Int
        get()=getInt(LAST_SAVED_VERSION_CODE)
        set(value) {
            putInt(LAST_SAVED_VERSION_CODE, value)
        }

    open var userId: String
        get()=getString(USER_ID_KEY)
        set(value) {
            putString(USER_ID_KEY, value)
        }
}
package com.kalashnyk.denys.defaultproject.utils

import android.Manifest

object ApplicationConstants {
    val CARD_DEFAULT = "default"
    val CARD_USER = "user"
    val CARD_FEED = "feed"
    val AUTH_TYPE_SCREEN = "auth_type_screen"
    val DETAIL_ID = "detail_id"
    val COLOR_PRIMARY_ALPHA = 255
    val splashTimer = 3000L

    val GALLERY_PERMISSION_REQUEST = 5
    val galleryArrayPermission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
}
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

    val SETTINGS_REQUEST = 3
    val GALLERY_REQUEST = 4
    val GALLERY_PERMISSION_REQUEST = 1001
    val CAMERA_PERMISSION_REQUEST = 1002
    val galleryArrayPermission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    val cameraArrayPermission= arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
}
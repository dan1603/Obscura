package com.kalashnyk.denys.defaultproject.utils

import android.Manifest

/**
 *  todo refactor by cases
 */
object ApplicationConstants {

    /**
     *
     */
    const val CARD_DEFAULT: String= "default"

    /**
     *
     */
    const val CARD_USER: String = "user"

    /**
     *
     */
    const val CARD_FEED: String = "feed"

    /**
     *
     */
    const val AUTH_TYPE_SCREEN: String = "auth_type_screen"

    /**
     *
     */
    const val DETAIL_ID: String = "detail_id"

    /**
     *
     */
    const val PHONE_LENGTH_WITHOUT_COUNTRY_CODE: Int= 10

    /**
     *
     */
    const val PHONE_LENGTH_WITH_COUNTRY_CODE: Int= 12

    /**
     *
     */
    const val COLOR_PRIMARY_ALPHA: Int = 255

    /**
     *
     */
    const val DELAY_3000: Long = 3000L

    /**
     *
     */
    const val SETTINGS_REQUEST: Int= 3

    /**
     *
     */
    const val GALLERY_REQUEST: Int= 4

    /**
     *
     */
    const val GALLERY_PERMISSION_REQUEST: Int= 1001

    /**
     *
     */
    const val CAMERA_PERMISSION_REQUEST: Int= 1002

    /**
     *
     */
    val galleryArrayPermission= arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    /**
     *
     */
    val cameraArrayPermission= arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
}
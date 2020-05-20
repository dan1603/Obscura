package com.kalashnyk.denys.defaultproject.utils

import android.Manifest

/**
 *  todo refactor by cases
 */

const val PROJECT_PREFERENCE: String="obscura_prefs"
/**
 *
 */
const val HEADER_APP_VERSION: String="App-Version"

/**
 *
 */
const val HEADER_APP_VERSION_HASH: String="App-Version-Hash"

/**
 *
 */
const val HEADER_BUILD_NUM: String="Build-Num"

/**
 *
 */
const val HEADER_COUNTRY: String="Country"

/**
 *
 */
const val HEADER_CONTENT_TYPE: String="Connection-Type"

/**
 *
 */
const val HEADER_CARRIER: String="Carrier"

/**
 *
 */
const val HEADER_OS_VERSION: String="OS-Version"

/**
 *
 */
const val HEADER_GOOGLE_ADVERTISER_ID: String="Google-Advertiser-Id"

/**
 *
 */
const val HEADER_DEVICE_NAME: String="Device-Name"

/**
 *
 */
const val HEADER_DEVICE_TYPE: String="Device-Type"

/**
 *
 */
const val HEADER_TIMEZONE: String="Timezone"

/**
 *
 */
const val HEADER_ANDROID_ID: String="Android-Id"

/**
 *
 */
const val DEVICE_TYPE_VALUE: String="2"

/**
 *
 */
const val CARD_DEFAULT: String="default"

/**
 *
 */
const val CARD_USER: String="user"

/**
 *
 */
const val CARD_EVENT: String="event"

/**
 *
 */
const val CARD_ARTICLE: String="article"


/**
 *
 */
const val DEFAULT_SCREEN: String="default_screen"
/**
 *
 */
const val EXTRAS_PAGES: String="extras_pages"
/**
 *
 */
const val CONTENT_PAGE_SIZE: Int=20

/**
 *
 */
const val DEFAULT_INITIAL_LOADED_KEY : Int = 0

/**
 *
 */
const val FIRST_LIST_POSITION : Int = 0

/**
 *
 */
const val MIN_LIST_SIZE : Int = 1

/**
 *
 */
const val DEFAULT_CACHE_VALUE: Int = 0

/**
 *
 */
const val CACHED_VALUE: Int= 1

/**
 *
 */
const val AUTH_TYPE_SCREEN: String="auth_type_screen"

/**
 *
 */
const val DETAIL_ID: String="detail_id"

/**
 *
 */
const val PHONE_LENGTH_WITHOUT_COUNTRY_CODE: Int=10

/**
 *
 */
const val PHONE_LENGTH_WITH_COUNTRY_CODE: Int=12

/**
 *
 */
const val COLOR_ALPHA_255: Int=255

/**
 *
 */
const val DELAY_3000: Long=3000L

/**
 *
 */
const val SETTINGS_REQUEST: Int=3

/**
 *
 */
const val GALLERY_REQUEST: Int=4

/**
 *
 */
const val GALLERY_PERMISSION_REQUEST: Int=1001

/**
 *
 */
const val CAMERA_PERMISSION_REQUEST: Int=1002

/**
 *
 */
val galleryArrayPermission=
    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

/**
 *
 */
val cameraArrayPermission=arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)


/**
 *
 */
const val DEFAULT_TIMEOUT: Int= 10

/**
 *
 */
const val DEFAULT_RETRY_ATTEMPTS: Long= 4L

// LOCATION PARAMS
const val LOCATION_REQUEST_INTERVAL = 1000L
const val REQUEST_CODE_LOCATION: Int = 5455
const val BUNDLE_LOCATION_LAT = "bundle_location_LAT"
const val BUNDLE_LOCATION_LNG = "bundle_location_LNG"
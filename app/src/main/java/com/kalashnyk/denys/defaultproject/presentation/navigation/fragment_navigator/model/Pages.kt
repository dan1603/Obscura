package com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model

enum class Pages(val text: String) {

    SIGN_IN("sign_in"),
    SIGN_UP("sign_up"),
    RECOVER_ACCOUNT("recover_account"),
    EDIT_PERSONAL_DATA("edit_personal_data"),
    EDIT_PROFESSIONAL_DATA("edit_professional_data"),
    THEMES_CALENDAR("themes_calendar"),
    CREATED_THEMES("created_themes"),
    FOLLOWED_THEMES("followed_themes"),
    APPLICATION_SETTINGS("application_settings"),
    UNKNOWN("unknown");

    override fun toString(): String {
        return text
    }

    companion object {
        @JvmStatic
        fun fromString(input: String?): Pages {
            return when {
                input == null || input.isEmpty() -> UNKNOWN
                else -> values().firstOrNull {
                    it.text.equals(
                        input,
                        ignoreCase = true
                    )
                } ?: UNKNOWN
            }
        }
    }
}
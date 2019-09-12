package com.kalashnyk.denys.defaultproject.presentation.navigation.model

enum class Pages(val text: String) {

    SIGN_IN("sign_in"),
    SIGN_UP("sign_up"),
    RECOVER_ACCOUNT("recover_account"),
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
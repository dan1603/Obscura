package com.kalashnyk.denys.defaultproject.presentation.navigation.model

enum class TransitionAnimation(private val text: String) {
    NONE(""),
    SLIDE_IN_FROM_RIGHT("SLIDE_IN_FROM_RIGHT"),
    SCALE_UP_FROM_VIEW("SCALE_UP_FROM_VIEW"),
    SLIDE_UP_FROM_BOTTOM("SLIDE_UP_FROM_BOTTOM"),
    ENTER_FROM_RIGHT("ENTER_FROM_RIGHT"),
    ENTER_FROM_LEFT("ENTER_FROM_LEFT"),
    FADE_IN("FADE_IN");

    companion object {

        fun fromString(input: String?): TransitionAnimation {
            var result = NONE
            if (input == null || input.isEmpty()) {
                result = NONE
            } else {
                for (type in values()) {
                    if (type.text.equals(input, ignoreCase = true)) {
                        result = type
                        break
                    }
                }
            }
            return result
        }
    }

    override fun toString(): String {
        return text
    }

}
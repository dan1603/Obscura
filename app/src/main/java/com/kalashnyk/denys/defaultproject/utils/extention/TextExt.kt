package com.kalashnyk.denys.defaultproject.utils.extention

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View

fun View.bindSpanText(
    spanString: Int,
    string: Int
): String {
    val spanFromRes=this.context.getString(spanString)
    val stringFromRes=this.context.getString(string)
    return spanStrings(spanFromRes, stringFromRes)
}

fun View.bindSpanTextWithResize(spanString: Int,
                                string: Int,
                                ) {

}

private fun spanStrings(spanText: String, text: String): String {

    val finalText="$text $spanText"

    val text=SpannableString(finalText)
    text.setSpan(
        StyleSpan(Typeface.BOLD),
        text.length + 1,
        spanText.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return text.toString()
}
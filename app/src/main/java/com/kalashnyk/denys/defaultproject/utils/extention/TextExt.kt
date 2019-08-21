package com.kalashnyk.denys.defaultproject.utils.extention

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View

fun View.prepareSpanText(
    spanText: String,
    text: String
): String=
    spanStrings(spanText, text).toString()


fun View.prepareSpanTextWithResize(
    spanText: String,
    text: String,
    spanTextSize: Int,
    textSize: Int
): String {
    val differentSizes : Float =  this.resources.getDimension(spanTextSize) / this.resources.getDimension(textSize)
    val span = spanStrings(spanText, text)
    span.setSpan(RelativeSizeSpan(differentSizes), 0, text.length +1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return span.toString()
}

private fun spanStrings(spanText: String, text: String): SpannableString=
    SpannableString("$text $spanText").apply {
        setSpan(
            StyleSpan(Typeface.BOLD),
            text.length + 1,
            spanText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

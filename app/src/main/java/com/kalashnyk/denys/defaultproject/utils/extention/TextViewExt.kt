package com.kalashnyk.denys.defaultproject.utils.extention

import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.IntegerRes
import androidx.core.content.ContextCompat

/**
 *
 */
fun TextView.clearErrorForTextView() {
    this.text=""
    this.updateVisibility(false)
}

/**
 *
 */
fun TextView.addErrorForTextView(error : String?) {
    this.text=error
    this.updateVisibility(true)
}

/**
 *
 */
fun TextView.textColor(textColorRes: Int): Unit = this.setTextColor(ContextCompat.getColor(this.context, textColorRes))

/**
 *
 */
fun TextView.textSizePX(textSizeRes: Int) : Unit = this.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.resources.getDimension(textSizeRes))
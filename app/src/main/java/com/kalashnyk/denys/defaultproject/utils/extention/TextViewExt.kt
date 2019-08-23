package com.kalashnyk.denys.defaultproject.utils.extention

import android.widget.TextView

fun TextView.clearErrorForTextView() {
    this.text=""
    this.updateVisibility(false)
}

fun TextView.addErrorForTextView(error : String?) {
    this.text=error
    this.updateVisibility(true)
}
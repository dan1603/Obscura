package com.kalashnyk.denys.defaultproject.utils.extention

import com.google.android.material.textfield.TextInputLayout


fun TextInputLayout.clearErrorForTextInputLayout() {
    this.apply {
        this.isHintEnabled=true
        this.isFocusable=false
        this.error=null
    }
}
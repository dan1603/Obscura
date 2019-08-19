package com.kalashnyk.denys.defaultproject.utils.extention

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.clearErrorForTextInputLayout() {
    this.apply {
        this.isHintEnabled=true
        this.error=null
        this.isErrorEnabled=false
    }
}

fun TextInputLayout.addErrorForTextInputLayout(error: String?) {
    this.apply {
        this.isHintEnabled=false
        this.isErrorEnabled=true
        this.error=error
    }
}


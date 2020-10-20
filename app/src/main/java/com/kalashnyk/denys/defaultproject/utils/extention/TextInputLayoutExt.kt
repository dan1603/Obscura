package com.kalashnyk.denys.defaultproject.utils.extention

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.clearErrorForTextInputLayout() {
    this.apply {
        this.error=null
        this.isErrorEnabled=false
    }
}

fun TextInputLayout.addErrorForTextInputLayout(error: String?) {
    this.apply {
        this.postDelayed({
            this.isErrorEnabled=true
            this.error=error
        },200)
    }
}


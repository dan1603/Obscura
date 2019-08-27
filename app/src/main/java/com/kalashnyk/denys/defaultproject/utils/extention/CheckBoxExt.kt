package com.kalashnyk.denys.defaultproject.utils.extention

import android.widget.CheckBox
import com.kalashnyk.denys.defaultproject.R

fun CheckBox.addErrorForCheckBox() {
    this.apply {
        this.buttonDrawable=
            this.context.getDrawable(R.drawable.ic_check_box_error)
    }
}

fun CheckBox.clearErrorForCheckBox() {
    this.apply {
        this.postDelayed({
            this.buttonDrawable=this.context.getDrawable(R.drawable.toggle_checkbox)
        }, 200)
    }
}


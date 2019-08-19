package com.kalashnyk.denys.defaultproject.utils.extention

import android.widget.CheckBox
import com.kalashnyk.denys.defaultproject.R

fun CheckBox.clearErrorForCheckBox() {
    this.apply {
        if (this.isChecked) {
            this.background=
                this.context.getDrawable(R.drawable.ic_check_box_active)

        } else {
            this.background=this.context.getDrawable(R.drawable.ic_check_box_normal)
        }
    }
}
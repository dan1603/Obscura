package com.kalashnyk.denys.defaultproject.utils.binding

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class TextBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("textSpan")
        fun bindSpanText(
            view: View,
            textSpanModel: TextSpanModel
        ) {
            val span = textSpanModel.prepareContent(view.context)
            if(view is TextView) view.setText(span, TextView.BufferType.SPANNABLE)
            if(view is CheckBox) view.setText(span, TextView.BufferType.SPANNABLE)
        }
    }
}

package com.kalashnyk.denys.defaultproject.utils.binding

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
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

        @JvmStatic
        @BindingAdapter("textWithCount")
        fun bindCountText(
            view: TextView,
            textModel: TextCountModel
        ) {
            val content = textModel.prepareContent(view.context)
            view.text=content.first
            view.setTextColor(ContextCompat.getColor(view.context, content.second))
        }
    }
}

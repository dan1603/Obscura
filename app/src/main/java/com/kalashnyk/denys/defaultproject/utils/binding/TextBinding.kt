package com.kalashnyk.denys.defaultproject.utils.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.kalashnyk.denys.defaultproject.utils.extention.prepareSpanText
import com.kalashnyk.denys.defaultproject.utils.extention.prepareSpanTextWithResize

class TextBinding {
    companion object {

        @JvmStatic
        @BindingAdapter(
            "bind:spanTextResize",
            "bind:textResize",
            "bind:spanTextSize",
            "bind:textSize"
        )
        fun bindSpanTextResize(
            view: TextView,
            spanText: String,
            text: String,
            spanTextSize: Int,
            textSize: Int
        ) {
            view.prepareSpanTextWithResize(spanText, text, spanTextSize, textSize)
        }

        @JvmStatic
        @BindingAdapter(
            "bind:spanText",
            "bind:text"
        )
        fun bindSpanText(
            view: TextView,
            spanText: String,
            text: String
        )=view.prepareSpanText(spanText, text)
    }
}

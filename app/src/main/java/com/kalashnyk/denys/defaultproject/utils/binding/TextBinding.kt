package com.kalashnyk.denys.defaultproject.utils.binding

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.kalashnyk.denys.defaultproject.utils.extention.textColor
import com.kalashnyk.denys.defaultproject.utils.extention.textSizePX

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class TextBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("bindText")
        fun bindText(
            view: View,
            textBindingModel: TextBindingModel
        ) {
            when(textBindingModel) {
                is TextBindingModel.Span -> {
                    val span = textBindingModel.prepareContent(view.context)
                    if(view is TextView) view.setText(span, TextView.BufferType.SPANNABLE)
                    if(view is CheckBox) view.setText(span, TextView.BufferType.SPANNABLE)
                }
                is TextBindingModel.CountFormat -> {
                    val content = textBindingModel.prepareContent(view.context)
                    if(view is TextView) {
                        view.text=content.first
                        view.textColor(content.second)
                    }
                }
                is TextBindingModel.Default -> {
                    if(view is TextView) {
                        view.text= textBindingModel.text
                        view.textColor(textBindingModel.textColorRes)
                        view.textSizePX(textBindingModel.textSizeRes)
                    }
                }
            }

        }
    }
}

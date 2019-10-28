package com.kalashnyk.denys.defaultproject.utils.binding

import android.view.View
import androidx.databinding.BindingAdapter

class ViewBinding {
    companion object{
        @JvmStatic
        @BindingAdapter("viewVisibility")
        fun visibility(view: View, isVisible: Boolean) {
            view.visibility=if (isVisible) View.VISIBLE else View.GONE
        }
    }
}
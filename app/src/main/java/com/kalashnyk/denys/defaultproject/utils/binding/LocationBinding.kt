package com.kalashnyk.denys.defaultproject.utils.binding

import android.location.Address
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.kalashnyk.denys.defaultproject.utils.extention.gone
import com.kalashnyk.denys.defaultproject.utils.extention.visible

class LocationBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("address")
        fun bindAddress(label: TextView, address: Address?) {
            address?.let {
                label.text=address.getAddressLine(0).toString()
            }
        }

        @JvmStatic
        @BindingAdapter("decorate")
        fun bindDecoration(view: View, decorate: Boolean) {
            view.apply { if (decorate) visible() else gone() }
        }
    }
}
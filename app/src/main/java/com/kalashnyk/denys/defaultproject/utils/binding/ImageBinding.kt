package com.kalashnyk.denys.defaultproject.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.kalashnyk.denys.defaultproject.utils.glide.GlideApp

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class ImageBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(view: ImageView, imageUrl: String?) {
            GlideApp
                .with(view.context)
                .load(imageUrl)
                .centerCrop()
//                .placeholder(R.drawable.loading_animation) // can also be a drawable
                .into(view)
        }
    }
}

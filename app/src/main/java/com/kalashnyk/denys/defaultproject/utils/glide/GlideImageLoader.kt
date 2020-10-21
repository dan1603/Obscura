package com.kalashnyk.denys.defaultproject.utils.glide

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import de.hdodenhof.circleimageview.CircleImageView

class GlideImageLoader(
    private val mImageView: ImageView?,
    private val mProgressBar: ProgressBar?
) {

    fun load(url: String?, options: RequestOptions?) {
        if (options == null || mImageView == null) return

        onConnecting()

        DispatchingProgressManager.expect(url, object : UIonProgressListener {

            override val granularityPercentage: Float
                get()=1.0f

            override fun onProgress(bytesRead: Long, expectedLength: Long) {
                if (mProgressBar != null) {
                    mProgressBar.progress=(100 * bytesRead / expectedLength).toInt()
                }
            }
        })
        val listener=object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?, model: Any?, target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                DispatchingProgressManager.forget(url)
                onFinished()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?, model: Any?, target: Target<Drawable>?,
                dataSource: DataSource?, isFirstResource: Boolean
            ): Boolean {

                DispatchingProgressManager.forget(url)
                onFinished()
                return false
            }
        }

        var request = GlideApp.with(mImageView.context).load(url)
        request = if (mImageView is CircleImageView) {
            request.circleCrop()
        } else {
            request.centerCrop().transition(DrawableTransitionOptions.withCrossFade(300))
        }

        request.apply(options)
            .priority(Priority.IMMEDIATE)
            .listener(listener)
            .into(mImageView)
    }


    private fun onConnecting() {
        if (mProgressBar != null) {
            mProgressBar.visibility=View.VISIBLE
        }
    }

    private fun onFinished() {
        if (mProgressBar != null && mImageView != null) {
            mProgressBar.visibility=View.GONE
            mImageView.visibility=View.VISIBLE
        }
    }
}
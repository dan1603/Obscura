package com.kalashnyk.denys.moduleproject.utils.glide

import android.graphics.Bitmap
import com.bumptech.glide.load.Key

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation

import java.security.MessageDigest

class RoundedCornersTransformation constructor(
        private val radius: Int,
        private val margin: Int = 0,
        private val cornerType: CornerType=CornerType.ALL
) : BitmapTransformation() {

    private val ID = "com.kalashnyk.denys.moduleproject.utils.glide.RoundedCornersTransformation"

    enum class CornerType {
        ALL,
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT,
        TOP, BOTTOM, LEFT, RIGHT,
        OTHER_TOP_LEFT, OTHER_TOP_RIGHT, OTHER_BOTTOM_LEFT, OTHER_BOTTOM_RIGHT,
        DIAGONAL_FROM_TOP_LEFT, DIAGONAL_FROM_TOP_RIGHT
    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        return TransformationUtils.roundedCorners(
            pool,
            toTransform,
            outWidth,
            outHeight,
            radius,
            margin,
            cornerType
        )
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update((ID + "(radius=" + radius + ", margin=" + margin
                + ", cornerType=" + cornerType.name + ")").toByteArray(Key.CHARSET))
    }

    override fun equals(other: Any?): Boolean = other is RoundedCornersTransformation

    override fun hashCode(): Int = ID.hashCode()
}

package com.kalashnyk.denys.moduleproject.utils.glide

import android.graphics.*
import android.os.Build
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.util.Preconditions
import com.bumptech.glide.util.Synthetic
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import com.kalashnyk.denys.moduleproject.utils.glide.RoundedCornersTransformation.CornerType.*


object TransformationUtils {

    private val MODELS_REQUIRING_BITMAP_LOCK = Arrays.asList("XT1097", "XT1085")

    private val BITMAP_DRAWABLE_LOCK = if (MODELS_REQUIRING_BITMAP_LOCK.contains(Build.MODEL) && Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1)
        ReentrantLock()
    else
        NoLock()

    fun grayscale(pool: BitmapPool, inBitmap: Bitmap): Bitmap {

        val toTransform = getAlphaSafeBitmap(pool, inBitmap)

        BITMAP_DRAWABLE_LOCK.lock()
        try {
            val canvas = Canvas(toTransform)
            val saturation = ColorMatrix()
            saturation.setSaturation(0f)
            val paint = Paint()
            paint.colorFilter = ColorMatrixColorFilter(saturation)
            canvas.drawBitmap(toTransform, 0f, 0f, paint)
            clear(canvas)
        } finally {
            BITMAP_DRAWABLE_LOCK.unlock()
        }

        if (toTransform != inBitmap) {
            pool.put(toTransform)
        }
        return toTransform
    }

    /* round transformation*/

    fun roundedCorners(pool: BitmapPool, inBitmap: Bitmap,
                       width: Int, height: Int, roundingRadius: Int, margin: Int, cornerType: RoundedCornersTransformation.CornerType
    ): Bitmap {
        Preconditions.checkArgument(width > 0, "width must be greater than 0.")
        Preconditions.checkArgument(height > 0, "height must be greater than 0.")
        Preconditions.checkArgument(roundingRadius > 0, "roundingRadius must be greater than 0.")

        // Alpha is required for this transformation.
        val toTransform = getAlphaSafeBitmap(pool, inBitmap)
        val result = pool.get(inBitmap.width, inBitmap.height, Bitmap.Config.ARGB_8888)

        result.setHasAlpha(true)

        val shader = BitmapShader(toTransform, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = shader
        BITMAP_DRAWABLE_LOCK.lock()
        try {
            val canvas = Canvas(result)
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            drawRoundRect(canvas, paint, toTransform.width.toFloat(), toTransform.height.toFloat(), roundingRadius, margin, cornerType)
            clear(canvas)
        } finally {
            BITMAP_DRAWABLE_LOCK.unlock()
        }

        if (toTransform != inBitmap) {
            pool.put(toTransform)
        }

        return result
    }

    private fun drawRoundRect(canvas: Canvas, paint: Paint, width: Float, height: Float, radius: Int, margin: Int, cornerType: RoundedCornersTransformation.CornerType) {
        val right = width - margin
        val bottom = height - margin

        when (cornerType) {
            ALL -> canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), right, bottom), radius.toFloat(), radius.toFloat(), paint)
            TOP_LEFT -> drawTopLeftRoundRect(canvas, paint, right, bottom, radius, margin)
            TOP_RIGHT -> drawTopRightRoundRect(canvas, paint, right, bottom, radius, margin)
            BOTTOM_LEFT -> drawBottomLeftRoundRect(canvas, paint, right, bottom, radius, margin)
            BOTTOM_RIGHT -> drawBottomRightRoundRect(canvas, paint, right, bottom, radius, margin)
            TOP -> drawTopRoundRect(canvas, paint, right, bottom, radius, margin)
            BOTTOM -> drawBottomRoundRect(canvas, paint, right, bottom, radius, margin)
            LEFT -> drawLeftRoundRect(canvas, paint, right, bottom, radius, margin)
            RIGHT -> drawRightRoundRect(canvas, paint, right, bottom, radius, margin)
            OTHER_TOP_LEFT -> drawOtherTopLeftRoundRect(canvas, paint, right, bottom, radius, margin)
            OTHER_TOP_RIGHT -> drawOtherTopRightRoundRect(canvas, paint, right, bottom, radius, margin)
            OTHER_BOTTOM_LEFT -> drawOtherBottomLeftRoundRect(canvas, paint, right, bottom, radius, margin)
            OTHER_BOTTOM_RIGHT -> drawOtherBottomRightRoundRect(canvas, paint, right, bottom, radius, margin)
            DIAGONAL_FROM_TOP_LEFT -> drawDiagonalFromTopLeftRoundRect(canvas, paint, right, bottom, radius, margin)
            DIAGONAL_FROM_TOP_RIGHT -> drawDiagonalFromTopRightRoundRect(canvas, paint, right, bottom, radius, margin)
        }
    }

    private fun drawTopLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float, radius: Int, margin: Int) {
        canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), (margin + 2 * radius).toFloat(), (margin + 2 * radius).toFloat()),
                radius.toFloat(), radius.toFloat(), paint)
        canvas.drawRect(RectF(margin.toFloat(), (margin + radius).toFloat(), (margin + radius).toFloat(), bottom), paint)
        canvas.drawRect(RectF((margin + radius).toFloat(), margin.toFloat(), right, bottom), paint)
    }

    private fun drawTopRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float, radius: Int, margin: Int) {
        canvas.drawRoundRect(RectF(right - 2 * radius, margin.toFloat(), right, (margin + 2 * radius).toFloat()), radius.toFloat(),
                radius.toFloat(), paint)
        canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), right - radius, bottom), paint)
        canvas.drawRect(RectF(right - radius, (margin + radius).toFloat(), right, bottom), paint)
    }

    private fun drawBottomLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float, radius: Int, margin: Int) {
        canvas.drawRoundRect(RectF(margin.toFloat(), bottom - 2 * radius, (margin + 2 * radius).toFloat(), bottom),
                radius.toFloat(), radius.toFloat(), paint)
        canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), (margin + 2 * radius).toFloat(), bottom - radius), paint)
        canvas.drawRect(RectF((margin + radius).toFloat(), margin.toFloat(), right, bottom), paint)
    }

    private fun drawBottomRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float, radius: Int, margin: Int) {
        canvas.drawRoundRect(RectF(right - 2 * radius, bottom - 2 * radius, right, bottom), radius.toFloat(),
                radius.toFloat(), paint)
        canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), right - radius, bottom), paint)
        canvas.drawRect(RectF(right - radius, margin.toFloat(), right, bottom - radius), paint)
    }

    private fun drawTopRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float, radius: Int, margin: Int) {
        canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), right, (margin + 2 * radius).toFloat()), radius.toFloat(), radius.toFloat(),
                paint)
        canvas.drawRect(RectF(margin.toFloat(), (margin + radius).toFloat(), right, bottom), paint)
    }

    private fun drawBottomRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float, radius: Int, margin: Int) {
        canvas.drawRoundRect(RectF(margin.toFloat(), bottom - 2 * radius, right, bottom), radius.toFloat(), radius.toFloat(),
                paint)
        canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), right, bottom - radius), paint)
    }

    private fun drawLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float, radius: Int, margin: Int) {
        canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), (margin + 2 * radius).toFloat(), bottom), radius.toFloat(), radius.toFloat(),
                paint)
        canvas.drawRect(RectF((margin + radius).toFloat(), margin.toFloat(), right, bottom), paint)
    }

    private fun drawRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float, radius: Int, margin: Int) {
        canvas.drawRoundRect(RectF(right - 2 * radius, margin.toFloat(), right, bottom), radius.toFloat(), radius.toFloat(),
                paint)
        canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), right - radius, bottom), paint)
    }

    private fun drawOtherTopLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float, radius: Int, margin: Int) {
        canvas.drawRoundRect(RectF(margin.toFloat(), bottom - 2 * radius, right, bottom), radius.toFloat(), radius.toFloat(),
                paint)
        canvas.drawRoundRect(RectF(right - 2 * radius, margin.toFloat(), right, bottom), radius.toFloat(), radius.toFloat(),
                paint)
        canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), right - radius, bottom - radius), paint)
    }

    private fun drawOtherTopRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float, radius: Int, margin: Int) {
        canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), (margin + 2 * radius).toFloat(), bottom), radius.toFloat(), radius.toFloat(),
                paint)
        canvas.drawRoundRect(RectF(margin.toFloat(), bottom - 2 * radius, right, bottom), radius.toFloat(), radius.toFloat(),
                paint)
        canvas.drawRect(RectF((margin + radius).toFloat(), margin.toFloat(), right, bottom - radius), paint)
    }

    private fun drawOtherBottomLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float, radius: Int, margin: Int) {
        canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), right, (margin + 2 * radius).toFloat()), radius.toFloat(), radius.toFloat(),
                paint)
        canvas.drawRoundRect(RectF(right - 2 * radius, margin.toFloat(), right, bottom), radius.toFloat(), radius.toFloat(),
                paint)
        canvas.drawRect(RectF(margin.toFloat(), (margin + radius).toFloat(), right - radius, bottom), paint)
    }

    private fun drawOtherBottomRightRoundRect(canvas: Canvas, paint: Paint, right: Float,
                                              bottom: Float, radius: Int, margin: Int) {
        canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), right, (margin + 2 * radius).toFloat()), radius.toFloat(), radius.toFloat(),
                paint)
        canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), (margin + 2 * radius).toFloat(), bottom), radius.toFloat(), radius.toFloat(),
                paint)
        canvas.drawRect(RectF((margin + radius).toFloat(), (margin + radius).toFloat(), right, bottom), paint)
    }

    private fun drawDiagonalFromTopLeftRoundRect(canvas: Canvas, paint: Paint, right: Float,
                                                 bottom: Float, radius: Int, margin: Int) {
        canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), (margin + 2 * radius).toFloat(), (margin + 2 * radius).toFloat()),
                radius.toFloat(), radius.toFloat(), paint)
        canvas.drawRoundRect(RectF(right - 2 * radius, bottom - 2 * radius, right, bottom), radius.toFloat(),
                radius.toFloat(), paint)
        canvas.drawRect(RectF(margin.toFloat(), (margin + radius).toFloat(), right - 2 * radius, bottom), paint)
        canvas.drawRect(RectF((margin + 2 * radius).toFloat(), margin.toFloat(), right, bottom - radius), paint)
    }

    private fun drawDiagonalFromTopRightRoundRect(canvas: Canvas, paint: Paint, right: Float,
                                                  bottom: Float, radius: Int, margin: Int) {
        canvas.drawRoundRect(RectF(right - 2 * radius, margin.toFloat(), right, (margin + 2 * radius).toFloat()), radius.toFloat(),
                radius.toFloat(), paint)
        canvas.drawRoundRect(RectF(margin.toFloat(), bottom - 2 * radius, (margin + 2 * radius).toFloat(), bottom),
                radius.toFloat(), radius.toFloat(), paint)
        canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), right - radius, bottom - radius), paint)
        canvas.drawRect(RectF((margin + radius).toFloat(), (margin + radius).toFloat(), right, bottom), paint)
    }

    private fun getAlphaSafeBitmap(pool: BitmapPool,
                                   maybeAlphaSafe: Bitmap): Bitmap {
        if (Bitmap.Config.ARGB_8888 == maybeAlphaSafe.config) {
            return maybeAlphaSafe
        }

        val argbBitmap = pool.get(maybeAlphaSafe.width, maybeAlphaSafe.height,
                Bitmap.Config.ARGB_8888)
        Canvas(argbBitmap).drawBitmap(maybeAlphaSafe, 0f, 0f, null)
        return argbBitmap
    }

    private fun clear(canvas: Canvas) {
        canvas.setBitmap(null)
    }

    private class NoLock @Synthetic internal constructor() : Lock {
        override fun lock() {}
        @Throws(InterruptedException::class)
        override fun lockInterruptibly() {}
        override fun tryLock(): Boolean = true
        @Throws(InterruptedException::class)
        override fun tryLock(time: Long, unit: TimeUnit): Boolean = true
        override fun unlock() {}
        override fun newCondition(): Condition {
            throw UnsupportedOperationException("Should not be called")
        }
    }
}

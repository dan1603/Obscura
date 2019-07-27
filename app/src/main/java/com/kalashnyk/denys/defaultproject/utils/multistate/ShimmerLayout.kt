package com.kalashnyk.denys.defaultproject.utils.multistate

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Point
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.Shader
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import com.kalashnyk.denys.defaultproject.R

class ShimmerLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    FrameLayout(context, attrs, defStyle) {

    private var maskOffsetX: Int = 0
    private var maskRect: Rect? = null
    private val maskPaint: Paint
    private var maskAnimator: ValueAnimator? = null

    private var localAvailableBitmap: Bitmap? = null
    private var localMaskBitmap: Bitmap? = null
    private var destinationBitmap: Bitmap? = null
    private var sourceMaskBitmap: Bitmap? = null
    private var canvasForRendering: Canvas? = null

    private var isAnimationStarted: Boolean = false
    private var autoStart: Boolean = false
    private var shimmerAnimationDuration: Int = 0
    private var shimmerColor: Int = 0
    private var shimmerAngle: Int = 0
    private var viewWidth: Int = 0

    private val shimmerAnimation: Animator
        get() {
            if (maskAnimator != null) {
                return maskAnimator as ValueAnimator
            }

            if (maskRect == null) {
                maskRect = calculateMaskRect()
            }

            val animationToX = width
            val animationFromX = -animationToX
            val shimmerBitmapWidth = maskRect!!.width()
            val shimmerAnimationFullLength = animationToX - animationFromX

            maskAnimator = ValueAnimator.ofFloat(0.0f, 1.0f)
            maskAnimator!!.duration = shimmerAnimationDuration.toLong()
            maskAnimator!!.repeatCount = ObjectAnimator.INFINITE

            val value = FloatArray(1)
            maskAnimator!!
                .addUpdateListener { animation ->
                    value[0] = animation.animatedValue as Float
                    maskOffsetX = (animationFromX + shimmerAnimationFullLength * value[0]).toInt()

                    if (maskOffsetX + shimmerBitmapWidth >= 0) {
                        invalidate()
                    }
                }

            return maskAnimator as ValueAnimator
        }

    init {

        setWillNotDraw(false)

        maskPaint = Paint()
        maskPaint.isAntiAlias = true
        maskPaint.isDither = true
        maskPaint.isFilterBitmap = true
        maskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ShimmerLayout, 0, 0
        )

        try {
            shimmerAngle = a.getInteger(
                R.styleable.ShimmerLayout_shimmer_angle,
                DEFAULT_ANGLE
            )
            shimmerAnimationDuration = a.getInteger(
                R.styleable.ShimmerLayout_shimmer_animation_duration,
                DEFAULT_ANIMATION_DURATION
            )
            shimmerColor = a.getColor(
                R.styleable.ShimmerLayout_shimmer_color,
                getColor(R.color.black_38)
            )
            autoStart = a.getBoolean(
                R.styleable.ShimmerLayout_shimmer_auto_start, false
            )
        } finally {
            a.recycle()
        }

        setShimmerAngle(shimmerAngle)
        if (autoStart && visibility == View.VISIBLE) {
            startShimmerAnimation()
        }
    }

    override fun onDetachedFromWindow() {
        resetShimmering()
        super.onDetachedFromWindow()
    }

    override fun dispatchDraw(canvas: Canvas) {
        if (!isAnimationStarted || width <= 0 || height <= 0) {
            super.dispatchDraw(canvas)
        } else {
            dispatchDrawUsingBitmap(canvas)
        }
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (visibility == View.VISIBLE) {
            if (autoStart) {
                startShimmerAnimation()
            }
        } else {
            stopShimmerAnimation()
        }
    }

    fun startShimmerAnimation() {
        if (isAnimationStarted) {
            return
        }
        if (viewWidth == 0) {
            viewTreeObserver.addOnGlobalLayoutListener(
                object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        removeGlobalLayoutListener(this)
                        startShimmerAnimation()
                        viewWidth = width
                    }
                })

            return
        }

        val animator = shimmerAnimation
        animator.start()
        isAnimationStarted = true
    }

    fun stopShimmerAnimation() {
        resetShimmering()
    }

    fun setShimmerColor(shimmerColor: Int) {
        this.shimmerColor = shimmerColor
        resetIfStarted()
    }

    fun setShimmerAnimationDuration(durationMillis: Int) {
        this.shimmerAnimationDuration = durationMillis
        resetIfStarted()
    }

    /**
     * Set the angle of the shimmer effect in clockwise direction in degrees.
     * The angle must be between {@value #MIN_ANGLE_VALUE} and
     * {@value #MAX_ANGLE_VALUE}.
     *
     * @param angle
     * The angle to be set
     */
    fun setShimmerAngle(angle: Int) {
        if (angle < MIN_ANGLE_VALUE || MAX_ANGLE_VALUE < angle) {
            throw IllegalArgumentException(
                String.format(
                    "shimmerAngle value must be between %d and %d",
                    MIN_ANGLE_VALUE, MAX_ANGLE_VALUE
                )
            )
        }
        this.shimmerAngle = angle
        resetIfStarted()
    }

    private fun resetIfStarted() {
        if (isAnimationStarted) {
            resetShimmering()
            startShimmerAnimation()
        }
    }

    private fun dispatchDrawUsingBitmap(canvas: Canvas) {
        super.dispatchDraw(canvas)

        localAvailableBitmap = getDestinationBitmap()
        if (localAvailableBitmap == null) {
            return
        }

        if (canvasForRendering == null) {
            canvasForRendering = Canvas(localAvailableBitmap!!)
        }

        drawMask(canvasForRendering!!)
        canvas.save()
        canvas.clipRect(
            maskOffsetX, 0, maskOffsetX + maskRect!!.width(),
            height
        )
        canvas.drawBitmap(localAvailableBitmap!!, 0f, 0f, null)
        canvas.restore()

        localAvailableBitmap = null
    }

    private fun drawMask(renderCanvas: Canvas) {
        localMaskBitmap = getSourceMaskBitmap()
        if (localMaskBitmap == null) {
            return
        }

        renderCanvas.save()
        renderCanvas.clipRect(
            maskOffsetX, 0,
            maskOffsetX + localMaskBitmap!!.width, height
        )

        super.dispatchDraw(renderCanvas)
        renderCanvas.drawBitmap(localMaskBitmap!!, maskOffsetX.toFloat(), 0f, maskPaint)

        renderCanvas.restore()

        localMaskBitmap = null
    }

    private fun resetShimmering() {
        if (maskAnimator != null) {
            maskAnimator!!.end()
            maskAnimator!!.removeAllUpdateListeners()
        }

        maskAnimator = null
        isAnimationStarted = false

        releaseBitMaps()
    }

    private fun releaseBitMaps() {
        if (sourceMaskBitmap != null) {
            sourceMaskBitmap!!.recycle()
            sourceMaskBitmap = null
        }

        if (destinationBitmap != null) {
            destinationBitmap!!.recycle()
            destinationBitmap = null
        }

        canvasForRendering = null
    }

    private fun getDestinationBitmap(): Bitmap? {
        if (destinationBitmap == null) {
            destinationBitmap = createBitmap(width, height)
        }

        return destinationBitmap
    }

    private fun getSourceMaskBitmap(): Bitmap {
        if (sourceMaskBitmap != null) {
            return sourceMaskBitmap as Bitmap
        }

        val width = maskRect!!.width()
        val height = height

        val edgeColor = reduceColorAlphaValueToZero(shimmerColor)
        val gradient = LinearGradient(
            (-maskRect!!.left).toFloat(), 0f,
            (width + maskRect!!.left).toFloat(), 0f,
            intArrayOf(edgeColor, shimmerColor, shimmerColor, edgeColor),
            floatArrayOf(0.25f, 0.47f, 0.53f, 0.75f),
            Shader.TileMode.CLAMP
        )
        val paint = Paint()
        paint.shader = gradient

        sourceMaskBitmap = createBitmap(width, height)
        val canvas = Canvas(sourceMaskBitmap!!)
        canvas.rotate(shimmerAngle.toFloat(), (width / 2).toFloat(), (height / 2).toFloat())
        canvas.drawRect(
            (-maskRect!!.left).toFloat(), maskRect!!.top.toFloat(), (width + maskRect!!.left).toFloat(),
            maskRect!!.bottom.toFloat(), paint
        )

        return sourceMaskBitmap as Bitmap
    }

    private fun createBitmap(width: Int, height: Int): Bitmap {
        try {
            return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        } catch (e: OutOfMemoryError) {
            System.gc()
            return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        }

    }

    private fun getColor(id: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.getColor(id)
        } else {

            resources.getColor(id)
        }
    }

    private fun removeGlobalLayoutListener(listener: ViewTreeObserver.OnGlobalLayoutListener) {
        viewTreeObserver.removeOnGlobalLayoutListener(listener)
    }

    private fun reduceColorAlphaValueToZero(actualColor: Int): Int {
        return Color.argb(
            0, Color.red(actualColor), Color.green(actualColor),
            Color.blue(actualColor)
        )
    }

    private fun calculateMaskRect(): Rect {
        val shimmerWidth = width / 2
        if (shimmerAngle == 0) {
            return Rect(
                (shimmerWidth * 0.25).toInt(), 0,
                (shimmerWidth * 0.75).toInt(), height
            )
        }

        val top = 0
        val center = (height * 0.5).toInt()
        val right = (shimmerWidth * 0.75).toInt()
        val originalTopRight = Point(right, top)
        val originalCenterRight = Point(right, center)

        val rotatedTopRight = rotatePoint(
            originalTopRight, shimmerAngle.toFloat(),
            (shimmerWidth / 2).toFloat(), (height / 2).toFloat()
        )
        val rotatedCenterRight = rotatePoint(
            originalCenterRight,
            shimmerAngle.toFloat(), (shimmerWidth / 2).toFloat(), (height / 2).toFloat()
        )
        val rotatedIntersection = getTopIntersection(
            rotatedTopRight,
            rotatedCenterRight
        )
        val halfMaskHeight = distanceBetween(
            rotatedCenterRight,
            rotatedIntersection
        )

        val paddingVertical = height / 2 - halfMaskHeight
        val paddingHorizontal = shimmerWidth - rotatedIntersection.x

        return Rect(
            paddingHorizontal, paddingVertical,
            shimmerWidth - paddingHorizontal,
            height - paddingVertical
        )
    }

    /**
     * Finds the intersection of the line and the top of the canvas
     *
     * @param p1
     * First point of the line of which the intersection with the
     * canvas should be determined
     * @param p2
     * Second point of the line of which the intersection with the
     * canvas should be determined
     * @return The point of intersection
     */
    private fun getTopIntersection(p1: Point, p2: Point): Point {
        val x1 = p1.x.toDouble()
        val x2 = p2.x.toDouble()
        val y1 = (-p1.y).toDouble()
        val y2 = (-p2.y).toDouble()
        // slope-intercept form of the line represented by the two points
        val m = (y2 - y1) / (x2 - x1)
        val b = y1 - m * x1
        // The intersection with the line represented by the top of the canvas
        val x = ((0 - b) / m).toInt()
        val y = 0
        return Point(x, y)
    }

    private fun rotatePoint(point: Point, degrees: Float, cx: Float, cy: Float): Point {
        val pts = FloatArray(2)
        pts[0] = point.x.toFloat()
        pts[1] = point.y.toFloat()

        val transform = Matrix()
        transform.setRotate(degrees, cx, cy)
        transform.mapPoints(pts)

        return Point(pts[0].toInt(), pts[1].toInt())
    }

    private fun distanceBetween(p1: Point, p2: Point): Int {
        return Math.ceil(
            Math.sqrt(Math.pow((p1.x - p2.x).toDouble(), 2.0) + Math.pow((p1.y - p2.y).toDouble(), 2.0))
        ).toInt()
    }

    companion object {

        private val DEFAULT_ANIMATION_DURATION = 1500
        private val DEFAULT_ANGLE = 20
        private val MIN_ANGLE_VALUE = 0
        private val MAX_ANGLE_VALUE = 30
    }
}

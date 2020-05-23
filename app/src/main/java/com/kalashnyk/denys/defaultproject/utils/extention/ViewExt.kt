package com.kalashnyk.denys.defaultproject.utils.extention

import android.content.Context
import android.graphics.Rect
import android.view.*
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.inputmethod.InputMethodManager
import android.widget.TextView

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.requestFocusWithKeyboard() {
    requestFocus()
    this.postDelayed({
        val imm=this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }, 200)
}

fun View?.hideKeyboardWithClearFocus() {
    this?.clearFocus()
    val imm=this?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(this?.windowToken, 0)
}

fun View.doOnPreDraw(action: (view: View) -> Unit) {
    val vto=viewTreeObserver
    vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            action(this@doOnPreDraw)
            when {
                vto.isAlive -> vto.removeOnPreDrawListener(this)
                else -> viewTreeObserver.removeOnPreDrawListener(this)
            }
            return true
        }
    })
}

fun View.updateVisibility(isVisible: Boolean) {
    if (isVisible) {
        visibility=View.VISIBLE
    } else {
        visibility=View.GONE
    }
}

fun View.updateInvisibility(isVisible: Boolean) {
    if (isVisible) {
        visibility=View.VISIBLE
    } else {
        visibility=View.INVISIBLE
    }
}

fun View.getWindowCoords(): Pair<Float, Float> {
    val coords=arrayOf(0, 0).toIntArray()
    this.getLocationInWindow(coords)
    return Pair(coords[0].toFloat(), coords[1].toFloat())
}

fun View.removeFromParent() {
    (parent as? ViewGroup)?.removeView(this)
}

fun setViewsVisibility(visibility: Int, vararg views: View) {
    views.forEach {
        it.visibility=visibility
    }
}

fun View.setXYOnClickListener(filterCoordinate: (MotionEvent) -> Boolean, onClick: () -> Unit) {
    val gestureDetector=GestureDetector(context, SingleTapConfirm())
    setOnTouchListener { v, event ->
        if (gestureDetector.onTouchEvent(event)) {
            if (filterCoordinate(event)) {
                onClick()
            }
            true
        } else false
    }
}

fun TextView.validateTextView(isValid: Boolean, errorMessage: String?) {
    this.updateVisibility(!isValid)
    if (!isValid) {
        this.text=errorMessage
    }
}

fun ViewGroup.findViewAt(x: Int, y: Int): View? {
    (0 until this.childCount)
        .map { this.getChildAt(it) }
        .forEach {
            when (it) {
                is ViewGroup -> {
                    val foundView=it.findViewAt(x, y)
                    if (foundView?.isShown ?: return@forEach) return foundView
                }
                else -> {
                    val location=IntArray(2)
                    it.getLocationOnScreen(location)
                    val rect=Rect(location[0], location[1], location[0] + it.width, location[1] + it.height)
                    if (rect.contains(x, y)) return it
                }
            }
        }
    return null
}

inline fun View.onGlobalLayout(crossinline onLayout: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            onLayout()
        }
    })
}

private class SingleTapConfirm : SimpleOnGestureListener() {
    override fun onSingleTapUp(event: MotionEvent): Boolean=true
}
package com.kalashnyk.denys.defaultproject.utils.multistate

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import androidx.annotation.LayoutRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.kalashnyk.denys.defaultproject.R


annotation class ViewState
class MultiStateView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mInflater: LayoutInflater? = null

    private var mContentView: View? = null

    private var mLoadingView: View? = null

    private var mErrorView: View? = null

    private var mEmptyView: View? = null

    private var mAnimateViewChanges = false

    @ViewState
    private var mViewState =
        VIEW_STATE_UNKNOWN

    var viewState: Int
        @ViewState
        get() = mViewState
        set(@ViewState state) {
            if (state != mViewState) {
                val previous = mViewState
                mViewState = state
                setView(previous)
            }
        }

    init {
        mInflater = LayoutInflater.from(context)
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.MultiStateView
        )

        val loadingViewResId = a.getResourceId(
            R.styleable.MultiStateView_msv_loadingView, -1
        )
        if (loadingViewResId > -1) {
            mLoadingView = mInflater!!.inflate(loadingViewResId, this, false)
            addView(mLoadingView, mLoadingView!!.layoutParams)
        }

        val emptyViewResId = a.getResourceId(
            R.styleable.MultiStateView_msv_emptyView, -1
        )
        if (emptyViewResId > -1) {
            mEmptyView = mInflater!!.inflate(emptyViewResId, this, false)
            addView(mEmptyView, mEmptyView!!.layoutParams)
        }

        val errorViewResId = a.getResourceId(
            R.styleable.MultiStateView_msv_errorView, -1
        )
        if (errorViewResId > -1) {
            mErrorView = mInflater!!.inflate(errorViewResId, this, false)
            addView(mErrorView, mErrorView!!.layoutParams)
        }

        val viewState = a.getInt(
            R.styleable.MultiStateView_msv_viewState,
            VIEW_STATE_CONTENT
        )
        mAnimateViewChanges = a.getBoolean(
            R.styleable.MultiStateView_msv_animateViewChanges, false
        )

        when (viewState) {
            VIEW_STATE_CONTENT -> mViewState =
                VIEW_STATE_CONTENT

            VIEW_STATE_ERROR -> mViewState =
                VIEW_STATE_ERROR

            VIEW_STATE_EMPTY -> mViewState =
                VIEW_STATE_EMPTY

            VIEW_STATE_LOADING -> mViewState =
                VIEW_STATE_LOADING

            VIEW_STATE_UNKNOWN -> mViewState =
                VIEW_STATE_UNKNOWN
            else -> mViewState =
                VIEW_STATE_UNKNOWN
        }

        a.recycle()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (mContentView == null)
            throw IllegalArgumentException("Content view is not defined")
        setView(VIEW_STATE_UNKNOWN)
    }

    /*
     * All of the addView methods have been overridden so that it can obtain the
     * content view via XML It is NOT recommended to add views into
     * MultiStateView via the addView methods, but rather use any of the
     * setViewForState methods to set views for their given ViewState
     * accordingly
     */
    override fun addView(child: View?) {
        if (isValidContentView(child))
            mContentView = child
        super.addView(child)
    }

    override fun addView(child: View, index: Int) {
        if (isValidContentView(child))
            mContentView = child
        super.addView(child, index)
    }

    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
        if (isValidContentView(child))
            mContentView = child
        super.addView(child, index, params)
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams) {
        if (isValidContentView(child))
            mContentView = child
        super.addView(child, params)
    }

    override fun addView(child: View, width: Int, height: Int) {
        if (isValidContentView(child))
            mContentView = child
        super.addView(child, width, height)
    }

    override fun addViewInLayout(
        child: View, index: Int,
        params: ViewGroup.LayoutParams
    ): Boolean {
        if (isValidContentView(child))
            mContentView = child
        return super.addViewInLayout(child, index, params)
    }

    override fun addViewInLayout(
        child: View, index: Int,
        params: ViewGroup.LayoutParams, preventRequestLayout: Boolean
    ): Boolean {
        if (isValidContentView(child))
            mContentView = child
        return super
            .addViewInLayout(child, index, params, preventRequestLayout)
    }

    fun getView(@ViewState state: Int): View? {
        when (state) {
            VIEW_STATE_LOADING -> return mLoadingView

            VIEW_STATE_CONTENT -> return mContentView

            VIEW_STATE_EMPTY -> return mEmptyView

            VIEW_STATE_ERROR -> return mErrorView

            else -> return null
        }
    }

    private fun hideLoadingView() {
        if (mLoadingView != null) {
            mLoadingView!!.visibility = View.GONE
            val shimmerLayout = mLoadingView!!.findViewById<View>(R.id.shimmerLayout)
            if (shimmerLayout != null) {
                if (shimmerLayout is ShimmerLayout) {
                    (shimmerLayout as ShimmerLayout).stopShimmerAnimation()
                } else {
                    (shimmerLayout as ShimmerFrameLayout).stopShimmerAnimation()
                }
            }
        }
    }

    private fun setView(@ViewState previousState: Int) {
        when (mViewState) {
            VIEW_STATE_LOADING -> {
                if (mLoadingView == null) {
                    throw NullPointerException("Loading View")
                }

                if (mContentView != null)
                    mContentView!!.visibility = View.GONE
                if (mErrorView != null)
                    mErrorView!!.visibility = View.GONE
                if (mEmptyView != null)
                    mEmptyView!!.visibility = View.GONE

                if (mAnimateViewChanges) {
                    animateLayoutChange(getView(previousState))
                } else {
                    mLoadingView!!.visibility = View.VISIBLE
                }
            }

            VIEW_STATE_EMPTY -> {
                if (mEmptyView == null) {
                    throw NullPointerException("Empty View")
                }

                hideLoadingView()
                if (mErrorView != null)
                    mErrorView!!.visibility = View.GONE
                if (mContentView != null)
                    mContentView!!.visibility = View.GONE

                if (mAnimateViewChanges) {
                    animateLayoutChange(getView(previousState))
                } else {
                    mEmptyView!!.visibility = View.VISIBLE
                }
            }

            VIEW_STATE_ERROR -> {
                if (mErrorView == null) {
                    throw NullPointerException("Error View")
                }

                hideLoadingView()
                if (mContentView != null)
                    mContentView!!.visibility = View.GONE
                if (mEmptyView != null)
                    mEmptyView!!.visibility = View.GONE

                if (mAnimateViewChanges) {
                    animateLayoutChange(getView(previousState))
                } else {
                    mErrorView!!.visibility = View.VISIBLE
                }
            }

            VIEW_STATE_CONTENT -> {
                if (mContentView == null) {
                    // Should never happen, the view should throw an exception
                    // if no content view is present upon creation
                    throw NullPointerException("Content View")
                }

                hideLoadingView()
                if (mErrorView != null)
                    mErrorView!!.visibility = View.GONE
                if (mEmptyView != null)
                    mEmptyView!!.visibility = View.GONE

                if (mAnimateViewChanges) {
                    animateLayoutChange(getView(previousState))
                } else {
                    mContentView!!.visibility = View.VISIBLE
                }
            }
            else -> {
                if (mContentView == null) {
                    throw NullPointerException("Content View")
                }
                hideLoadingView()
                if (mErrorView != null)
                    mErrorView!!.visibility = View.GONE
                if (mEmptyView != null)
                    mEmptyView!!.visibility = View.GONE
                if (mAnimateViewChanges) {
                    animateLayoutChange(getView(previousState))
                } else {
                    mContentView!!.visibility = View.VISIBLE
                }
            }
        }
    }

    /**
     * Checks if the given [View] is valid for the Content View
     *
     * @param view
     * The [View] to check
     * @return
     */
    private fun isValidContentView(view: View?): Boolean {
        return if (mContentView != null && mContentView !== view) {
            false
        } else view !== mLoadingView && view !== mErrorView && view !== mEmptyView

    }

    @JvmOverloads
    fun setViewForState(
        view: View, @ViewState state: Int,
        switchToState: Boolean = false
    ) {
        when (state) {
            VIEW_STATE_LOADING -> {
                if (mLoadingView != null)
                    removeView(mLoadingView)
                mLoadingView = view
                addView(mLoadingView)
            }

            VIEW_STATE_EMPTY -> {
                if (mEmptyView != null)
                    removeView(mEmptyView)
                mEmptyView = view
                addView(mEmptyView)
            }

            VIEW_STATE_ERROR -> {
                if (mErrorView != null)
                    removeView(mErrorView)
                mErrorView = view
                addView(mErrorView)
            }

            VIEW_STATE_CONTENT -> {
                if (mContentView != null)
                    removeView(mContentView)
                mContentView = view
                addView(mContentView)
            }
        }

        setView(VIEW_STATE_UNKNOWN)
        if (switchToState)
            viewState = state
    }

    @JvmOverloads
    fun setViewForState(
        @LayoutRes layoutRes: Int, @ViewState state: Int,
        switchToState: Boolean = false
    ) {
        if (mInflater == null)
            mInflater = LayoutInflater.from(context)
        val view = mInflater!!.inflate(layoutRes, this, false)
        setViewForState(view, state, switchToState)
    }

    fun setAnimateLayoutChanges(animate: Boolean) {
        mAnimateViewChanges = animate
    }

    private fun animateLayoutChange(previousView: View?) {
        if (previousView == null) {
            getView(mViewState)!!.visibility = View.VISIBLE
            return
        }

        previousView.visibility = View.VISIBLE
        val anim = ObjectAnimator.ofFloat(
            previousView, "alpha",
            1.0f, 0.0f
        ).setDuration(250L)
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                previousView.visibility = View.GONE
                getView(mViewState)!!.visibility = View.VISIBLE
                ObjectAnimator
                    .ofFloat(getView(mViewState), "alpha", 0.0f, 1.0f)
                    .setDuration(250L).start()
            }
        })
        anim.start()
    }

    companion object {

        const val VIEW_STATE_UNKNOWN = -1

        const val VIEW_STATE_CONTENT = 0

        const val VIEW_STATE_ERROR = 1

        const val VIEW_STATE_EMPTY = 2

        const val VIEW_STATE_LOADING = 3
    }
}
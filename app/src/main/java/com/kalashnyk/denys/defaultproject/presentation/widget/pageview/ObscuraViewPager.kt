package com.kalashnyk.denys.defaultproject.presentation.widget.pageview

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class ObscuraViewPager : ViewPager {

    var pagerAdapter: PagerAdapter? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        pagerAdapter?.let {
            super.setAdapter(it)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        super.setAdapter(null)
        pagerAdapter = null
    }

    override fun setAdapter(adapter: PagerAdapter?) {
        pagerAdapter = adapter
    }

    fun isFirstPage(): Boolean = currentItem == 0

    fun isLastPage(): Boolean = currentItem == getFragmentCount() - 1

    fun getFragmentCount(): Int = adapter?.count ?: 0
}
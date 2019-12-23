package com.kalashnyk.denys.defaultproject.presentation.widget.pageview

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.Pages
import com.kalashnyk.denys.defaultproject.presentation.widget.pageview.model.TabPages

abstract class PagesAdapter<C : TabPages>(fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    private val items: MutableList<C> = mutableListOf()
    private var fragmentList: MutableMap<Int, Fragment> = mutableMapOf()
    private var fragmentTitleList: MutableMap<Int, String> = mutableMapOf()

    fun addItems(items: List<C>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun finishUpdate(container: ViewGroup) {
        try {
            super.finishUpdate(container)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getPageItem(position: Int): C = items[position]

    fun getFragmentItem(position: Int) = fragmentList[position]

    override fun getItem(position: Int): Fragment = getTab(getPageItem(position)).second.also {
        fragmentList[position] = it
    }

    override fun destroyItem(container: ViewGroup, position: Int, item: Any) {
        super.destroyItem(container, position, item)
        fragmentList.remove(position)
    }

    override fun getCount(): Int = items.size

    override fun getPageTitle(position: Int): CharSequence = getTab(getPageItem(position)).first.also {
        fragmentTitleList[position] = it
    }

    abstract fun getTab(card: C): Pair<String,Fragment>
}
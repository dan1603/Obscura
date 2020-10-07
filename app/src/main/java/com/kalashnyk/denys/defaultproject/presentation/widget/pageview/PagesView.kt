package com.kalashnyk.denys.defaultproject.presentation.widget.pageview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.presentation.fragments.list_messages.ListMessagesFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.list_users.ListUsersFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.list_themes.ListThemesFragment
import com.kalashnyk.denys.defaultproject.presentation.widget.pageview.model.TabPages
import com.kalashnyk.denys.defaultproject.presentation.widget.pageview.model.TabPages.*
import com.kalashnyk.denys.defaultproject.utils.extention.inflateLayout
import kotlinx.android.synthetic.main.view_pages.view.*

/**
 *
 */
class PagesView(context: Context, attr: AttributeSet?) : FrameLayout(context, attr),
    LifecycleOwner by context as LifecycleOwner, View.OnAttachStateChangeListener {

    init {
        Log.d("CheckPagesView", "PagesView init")
        inflateLayout(R.layout.view_pages)
    }

    fun addContent(pages: List<TabPages>) {
        adapter.addItems(pages)
        obscura_pager.adapter=adapter
        obscura_tabs.setupWithViewPager(obscura_pager)
    }

    /**
     *
     */
    val adapter: PagesAdapter<TabPages> by lazy {
        object : PagesAdapter<TabPages>(
            (context as AppCompatActivity).supportFragmentManager
        ) {
            override fun getTab(tabPages: TabPages): Pair<String, Fragment> {
                return when (tabPages) {
                    TAB_FOLLOWING -> Pair(context.getString(tabPages.resource), ListUsersFragment.newInstance(tabPages))
                    TAB_PEOPLE -> Pair(context.getString(tabPages.resource), ListUsersFragment.newInstance(tabPages))
                    TAB_THEMES -> Pair(context.getString(tabPages.resource), ListThemesFragment.newInstance(tabPages))
                    TAB_EVENTS -> Pair(context.getString(tabPages.resource), ListThemesFragment.newInstance(tabPages))
                    TAB_ARTICLES -> Pair(context.getString(tabPages.resource), ListThemesFragment.newInstance(tabPages))
                    TAB_FEED -> Pair(context.getString(tabPages.resource), ListThemesFragment.newInstance(tabPages))
                    TAB_MESSAGES -> Pair(context.getString(tabPages.resource), ListMessagesFragment.newInstance(tabPages))
                }
            }
        }
    }

    override fun onViewAttachedToWindow(v: View?) {
        Log.d("CheckPagesView", "PagesView onViewAttachedToWindow")
    }

    override fun onViewDetachedFromWindow(v: View?) {
        Log.d("CheckPagesView", "PagesView onViewDetachedFromWindow")
    }
}
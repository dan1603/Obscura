package com.kalashnyk.denys.defaultproject.presentation.fragments.main_tabs

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.kalashnyk.denys.defaultproject.presentation.widget.pageview.PagesView
import com.kalashnyk.denys.defaultproject.presentation.widget.pageview.model.TabPages

/**
 *
 */
class MainTabsBindingModel(private var tabsPages: List<TabPages>) : BaseObservable() {

    /**
     * @field pages
     */
    val tabs: List<TabPages>
        @Bindable get() {
            return tabsPages
        }

    companion object {

        @JvmStatic
        @BindingAdapter("bind:tabs")
        fun bindTabs(pagesView: PagesView, pages: List<TabPages>)=pagesView.addContent(pages=pages)
    }
}
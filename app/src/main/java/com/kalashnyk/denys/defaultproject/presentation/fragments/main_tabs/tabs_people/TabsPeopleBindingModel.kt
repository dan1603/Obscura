package com.kalashnyk.denys.defaultproject.presentation.fragments.main_tabs.tabs_people

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.kalashnyk.denys.defaultproject.BR
import com.kalashnyk.denys.defaultproject.presentation.widget.pageview.model.TabPages

class TabsPeopleBindingModel (private var tabsPages: List<TabPages>) : BaseObservable() {

    /**
     * @field pages
     */
    var pages: List<TabPages> = tabsPages
        set(value) {
            field=value
            notifyPropertyChanged(BR.pages)
        }
        @Bindable get() {
            return tabsPages
        }
}

package com.kalashnyk.denys.defaultproject.presentation.fragments.themes

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableBoolean
import com.kalashnyk.denys.defaultproject.BR

class FeedFlowModelBinding : BaseObservable() {

    var refreshing = ObservableBoolean()
        set(value) {
            field=value
            notifyPropertyChanged(BR.refreshing)
        }
        @Bindable get() {
            return refreshing
        }

    var loading = ObservableBoolean()
        set(value) {
            field=value
            notifyPropertyChanged(BR.loading)
        }
        @Bindable get() {
            return loading
        }

    var fetched = ObservableBoolean()
        set(value) {
            field=value
            notifyPropertyChanged(BR.fetched)
        }
        @Bindable get() {
            return fetched
        }
}
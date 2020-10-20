package com.kalashnyk.denys.defaultproject.presentation.base

interface ItemsLoadListener<T> {

    fun onItemsLoaded(item: T?)

    fun displayProgress()

    fun onLoadError(errorMessage: String)
}
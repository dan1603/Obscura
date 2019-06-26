package com.kalashnyk.denys.defaultproject.domain

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.support.annotation.StringRes
import com.kalashnyk.denys.defaultproject.App

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    fun getContext() = getApplication<App>()
    fun getString(@StringRes id: Int): String = getContext().getString(id)
}
package com.kalashnyk.denys.defaultproject.domain

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.kalashnyk.denys.defaultproject.presentation.base.LoadingState
import com.kalashnyk.denys.defaultproject.presentation.base.UiError

abstract class BaseViewModel : ViewModel() {

    val errorData = MediatorLiveData<UiError>()
    val macroLoadingState = MediatorLiveData<LoadingState>()

    internal fun clearError() {
        errorData.postValue(null)
    }
}
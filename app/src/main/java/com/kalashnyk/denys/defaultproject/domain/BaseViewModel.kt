package com.kalashnyk.denys.defaultproject.domain

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.kalashnyk.denys.defaultproject.presentation.base.LoadingState
import com.kalashnyk.denys.defaultproject.presentation.base.UiError
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable=CompositeDisposable()
    val errorData = MediatorLiveData<UiError>()
    val macroLoadingState = MediatorLiveData<LoadingState>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
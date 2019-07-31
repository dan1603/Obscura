package com.kalashnyk.denys.defaultproject.domain

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import com.kalashnyk.denys.defaultproject.presentation.widget.SingleLiveEvent
import com.kalashnyk.denys.defaultproject.usecases.Interactor
import com.kalashnyk.denys.defaultproject.usecases.repository.database.entity.UserEntity
//todo create paging list utils
//todo create mapper
//todo create multicontentview
//todo create rxutils
class AllUsersViewModel(application: Application, private val interactor: Interactor) : BaseViewModel(application) {
    private val liveDataItems = SingleLiveEvent<List<UserEntity>>()

    @SuppressLint("CheckResult")
    fun getAllItems() {
        interactor.getAll()?.subscribe { list -> liveDataItems.value = list }
    }

    fun getLiveDataItems(): LiveData<List<UserEntity>> {
        return liveDataItems
    }

    override fun onCleared() {
        super.onCleared()
    }
}


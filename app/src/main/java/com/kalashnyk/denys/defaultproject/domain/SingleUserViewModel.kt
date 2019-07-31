package com.kalashnyk.denys.defaultproject.domain

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import com.kalashnyk.denys.defaultproject.presentation.widget.SingleLiveEvent
import com.kalashnyk.denys.defaultproject.usecases.Interactor
import com.kalashnyk.denys.defaultproject.usecases.repository.database.entity.UserEntity

class SingleUserViewModel(application: Application, private val interactor: Interactor) : BaseViewModel(application) {

    private val liveDataItem = SingleLiveEvent<UserEntity>()

    @SuppressLint("CheckResult")
    fun getItem(id: Int) {
        interactor.getUser(id).subscribe { list -> liveDataItem.value = list }
    }

    fun getLiveDataItem(): LiveData<UserEntity> {
        return liveDataItem
    }
}
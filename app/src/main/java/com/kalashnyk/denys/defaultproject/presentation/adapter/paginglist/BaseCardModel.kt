package com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist

import android.databinding.BaseObservable
import com.kalashnyk.denys.defaultproject.usecases.repository.database.entity.UserEntity

abstract class BaseCardModel : BaseObservable() {

    abstract fun getCardId(): Int
    abstract fun getCardType(): String
    abstract fun getEntity(): UserEntity
}
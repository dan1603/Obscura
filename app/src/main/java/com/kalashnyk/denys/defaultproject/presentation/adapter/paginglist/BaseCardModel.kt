package com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist

import androidx.databinding.BaseObservable
import com.kalashnyk.denys.defaultproject.data.BaseModel

/**
 *
 */
abstract class BaseCardModel : BaseObservable() {

    /**
     *
     */
    abstract fun getCardId(): Int
    /**
     *
     */
    abstract fun getCardType(): String
    /**
     *
     */
    abstract fun getBaseModel(): BaseModel
}
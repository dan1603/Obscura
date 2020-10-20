package com.kalashnyk.denys.moduleproject.data_source.data

import androidx.databinding.BaseObservable


/**
 *
 */
abstract class BaseCardModel : BaseObservable() {
    /**
     *
     */
    abstract fun onClick()
    /**
     *
     */
    abstract fun getCardId(): String
    /**
     *
     */
    abstract fun getCardType(): String
    /**
     *
     */
    abstract fun getBaseModel(): BaseModel
}
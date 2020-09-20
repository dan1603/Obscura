package com.kalashnyk.denys.defaultproject.presentation.item

import com.kalashnyk.denys.defaultproject.data.BaseModel

interface ItemClickListener<T: BaseModel> {
    fun onClick(item: T)
}
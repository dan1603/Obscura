package com.kalashnyk.denys.moduleproject.data_source.data

interface ItemClickListener<T: BaseModel> {
    fun onClick(item: T)
}
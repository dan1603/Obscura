package com.kalashnyk.denys.defaultproject.data.card_models

import com.kalashnyk.denys.defaultproject.data.BaseModel
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.presentation.item.ItemClickListener
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.UserEntity
import com.kalashnyk.denys.defaultproject.utils.CARD_USER

/**
 *
 */
class UserCardModel(var user : UserEntity, private var listener: ItemClickListener<UserEntity>) : BaseCardModel() {
    override fun onClick() {
        listener.onClick(user)
    }

    fun getFullName(): String{
        return "${user.name} ${user.surname}"
    }

    fun getLocationToString(): String{
        return "${user.location?.country}, ${user.location?.state}, ${user.location?.city}"
    }

    override fun getCardId(): String {
        return user.id.toString()
    }

    override fun getCardType(): String {
        return CARD_USER
    }

    override fun getBaseModel(): BaseModel {
            return user
    }
}
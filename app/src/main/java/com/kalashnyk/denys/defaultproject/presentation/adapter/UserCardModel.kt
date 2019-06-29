package com.kalashnyk.denys.defaultproject.presentation.adapter

import com.kalashnyk.denys.defaultproject.usecases.repository.database.entity.UserEntity
import com.kalashnyk.denys.defaultproject.utils.ApplicationConstants

class UserCardModel(var user : UserEntity) : BaseCardModel() {

    override fun getCardId(): Int {
        return user.id
    }

    override fun getCardType(): String {
        return ApplicationConstants.CARD_USER
    }

    override fun getEntity(): UserEntity {
            return user
    }
}
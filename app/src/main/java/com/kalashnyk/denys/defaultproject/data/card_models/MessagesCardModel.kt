package com.kalashnyk.denys.defaultproject.data.card_models

import com.kalashnyk.denys.defaultproject.data.BaseModel
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.MessagesEntity
import com.kalashnyk.denys.defaultproject.utils.CARD_MESSAGES

/**
 *
 */
class MessagesCardModel(private var messages : MessagesEntity) : BaseCardModel() {

    override fun getCardId(): String {
        return messages.id.toString()
    }

    override fun getCardType(): String {
        return CARD_MESSAGES
    }

    override fun getBaseModel(): BaseModel {
            return messages
    }
}
package com.kalashnyk.denys.moduleproject.data_source.data.card_models

import com.kalashnyk.denys.moduleproject.data_source.CARD_ARTICLE
import com.kalashnyk.denys.moduleproject.data_source.data.BaseCardModel
import com.kalashnyk.denys.moduleproject.data_source.data.BaseModel
import com.kalashnyk.denys.moduleproject.data_source.data.ItemClickListener
import com.kalashnyk.denys.moduleproject.data_source.database.entity.ThemeEntity

class ArticleCardModel(var feed : ThemeEntity, private var listener: ItemClickListener<ThemeEntity>) : BaseCardModel() {
    override fun onClick() {
        listener.onClick(feed)
    }

    override fun getCardId(): String {
        return feed.id.toString()
    }

    override fun getCardType(): String {
        return CARD_ARTICLE
    }

    override fun getBaseModel(): BaseModel {
        return feed
    }
}
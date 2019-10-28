package com.kalashnyk.denys.defaultproject.utils

import com.kalashnyk.denys.defaultproject.data.BaseModel
import com.kalashnyk.denys.defaultproject.data.card_models.ArticleCardModel
import com.kalashnyk.denys.defaultproject.data.card_models.EventCardModel
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.data.card_models.UserCardModel
import com.kalashnyk.denys.defaultproject.usecases.repository.database.entity.ThemeEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.database.entity.UserEntity
import java.util.ArrayList

class ConverterFactory {

    fun convert(list: List<BaseModel>): List<BaseCardModel> {

        val items = ArrayList<BaseCardModel>()

        list.forEach {
            convert(it)?.apply {
                items.add(this)
            }
        }
        return items
    }

    private fun convert(item: BaseModel): BaseCardModel? {
        if(item is UserEntity){
            return UserCardModel(item)
        }else if(item is ThemeEntity && item.isEvent){
            return EventCardModel(item)
        }else if(item is ThemeEntity && item.isArticle){
            return ArticleCardModel(item)
        }
        return null
    }
}

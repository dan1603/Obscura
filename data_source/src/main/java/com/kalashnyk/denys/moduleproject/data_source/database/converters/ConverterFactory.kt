package com.kalashnyk.denys.moduleproject.data_source.database.converters

import com.kalashnyk.denys.moduleproject.data_source.data.BaseCardModel
import com.kalashnyk.denys.moduleproject.data_source.data.BaseModel
import com.kalashnyk.denys.moduleproject.data_source.data.ItemClickListener
import com.kalashnyk.denys.moduleproject.data_source.data.card_models.ArticleCardModel
import com.kalashnyk.denys.moduleproject.data_source.data.card_models.EventCardModel
import com.kalashnyk.denys.moduleproject.data_source.data.card_models.MessagesCardModel
import com.kalashnyk.denys.moduleproject.data_source.data.card_models.UserCardModel
import com.kalashnyk.denys.moduleproject.data_source.database.entity.MessagesEntity
import com.kalashnyk.denys.moduleproject.data_source.database.entity.ThemeEntity
import com.kalashnyk.denys.moduleproject.data_source.database.entity.UserEntity
import java.util.ArrayList

class ConverterFactory(private val clickListener: ItemClickListener<*>) {

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
        if(item is UserEntity) {
            return UserCardModel(item, clickListener as ItemClickListener<UserEntity>)
        }else if(item is MessagesEntity){
            return MessagesCardModel(item, clickListener as ItemClickListener<MessagesEntity>)
        }else if(item is ThemeEntity && item.isEvent){
            return EventCardModel(item, clickListener as ItemClickListener<ThemeEntity>)
        }else if(item is ThemeEntity && item.isArticle){
            return ArticleCardModel(item, clickListener as ItemClickListener<ThemeEntity>)
        }
        return null
    }
}

package com.kalashnyk.denys.defaultproject.utils

import android.app.Activity
import com.kalashnyk.denys.defaultproject.presentation.adapter.BaseCardModel
import com.kalashnyk.denys.defaultproject.presentation.adapter.UserCardModel
import com.kalashnyk.denys.defaultproject.usecases.repository.database.entity.UserEntity
import java.util.ArrayList

class ConverterFactory {

    fun convert(feed: List<UserEntity>): List<BaseCardModel> {

        val items = ArrayList<BaseCardModel>()

        for (i in feed.indices) {
            val item = feed[i]
            val model = convert(item)
            if (model != null) {
                items.add(model)
            }
        }

        return items
    }

    private fun convert(item: UserEntity): BaseCardModel? {
        return UserCardModel(item)
    }
}

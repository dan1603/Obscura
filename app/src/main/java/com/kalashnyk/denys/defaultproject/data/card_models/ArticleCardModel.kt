package com.kalashnyk.denys.defaultproject.data.card_models

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.kalashnyk.denys.defaultproject.data.BaseModel
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.ThemeEntity
import com.kalashnyk.denys.defaultproject.utils.CARD_ARTICLE
import com.squareup.picasso.Picasso

class ArticleCardModel(var feed : ThemeEntity) : BaseCardModel() {
    override fun onClick() {
        //TODO
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

    companion object{
        @BindingAdapter("imageUrl")
        fun setImageUrl(view: ImageView, poserPath: String) {
            Picasso.get().load("https://i.imgur.com/tGbaZCY.jpg").into(view)
        }
    }
}
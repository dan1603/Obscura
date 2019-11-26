package com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.kalashnyk.denys.defaultproject.data.BaseModel
import com.kalashnyk.denys.defaultproject.utils.*

/**
 *
 */
@Entity(tableName = "themes")
data class ThemeEntity(
    /**
     *
     */
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,
    /**
     *
     */
    @SerializedName("type")
    val type: String,
    /**
     *
     */
    @SerializedName("title")
    val title: String,
    /**
     *
     */
    @SerializedName("short_description")
    val shortDescription: String,
    //todo make category with object
    /**
     *
     */
    @SerializedName("category")
    val category: String,
    /**
     *
     */
    var screenType :String? = DEFAULT_SCREEN,
    /**
     *
     */
    var cached : Int? = DEFAULT_CACHE_VALUE
) : BaseModel() {

    /**
     *
     */
    val isEvent: Boolean
        get()=CARD_EVENT == type

    /**
     *
     */
    val isArticle: Boolean
        get()=CARD_ARTICLE == type
}
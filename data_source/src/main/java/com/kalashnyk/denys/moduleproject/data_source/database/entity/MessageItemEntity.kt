package com.kalashnyk.denys.moduleproject.data_source.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.kalashnyk.denys.moduleproject.data_source.CACHED_VALUE
import com.kalashnyk.denys.moduleproject.data_source.DEFAULT_CACHE_VALUE
import com.kalashnyk.denys.moduleproject.data_source.data.BaseModel

/**
 *
 */
@Entity(tableName = "messageItem")
data class MessageItemEntity(
    /**
     *
     */
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,
    /**
     *
     */
    @SerializedName("fromUserId")
    val fromUserId: Int,
    /**
     *
     */
    @SerializedName("toUserId")
    val toUserId: Int,
    /**
     *
     */
    @SerializedName("text")
    val text: String,
    /**
     *
     */
    var cached : Int? = DEFAULT_CACHE_VALUE
) : BaseModel() {

    fun convertItemForDataSource(item : MessageItemEntity, isCached: Boolean?) : MessageItemEntity {
        isCached?.let { item.cached = CACHED_VALUE }
        return item
    }
}
package com.kalashnyk.denys.moduleproject.data_source.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.kalashnyk.denys.moduleproject.data_source.CACHED_VALUE
import com.kalashnyk.denys.moduleproject.data_source.DEFAULT_CACHE_VALUE
import com.kalashnyk.denys.moduleproject.data_source.DEFAULT_SCREEN
import com.kalashnyk.denys.moduleproject.data_source.data.BaseModel

/**
 *
 */
@Entity(tableName = "messages")
data class MessagesEntity(
    /**
     *
     */
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,
    /**
     *
     */
    @SerializedName("talkerId")
    val talkerId: Int,
    /**
     *
     */
    @SerializedName("talkerName")
    val talkerName: String,
    /**
     *
     */
    @SerializedName("messageList")
    val messageList: ArrayList<MessageItemEntity>,
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
    fun getPreviewMessage(): String{
        if(messageList.isEmpty()) {
            return ""
        }
        val last = messageList[0].text
        if(last.length > 35){
            return last.substring(0, 35) + " ..."
        }
        return last
    }

    fun convertItemForDataSource(item : MessagesEntity, isCached: Boolean?, screenType : String?) : MessagesEntity {
        isCached?.let { item.cached = CACHED_VALUE }
        screenType?.let { item.screenType = it }
        return item
    }
}



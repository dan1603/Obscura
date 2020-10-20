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
@Entity(tableName="users")
data class UserEntity(

    /**
     *
     */
    @PrimaryKey(autoGenerate=true)
    @SerializedName("id")
    val id: Int,

    /**
     *
     */
    @SerializedName("name")
    var name: String? = null,

    /**
     *
     */
    @SerializedName("surname")
    var surname: String? = null,

    /**
     *
     */
    @SerializedName("fathername")
    val fathername: String? = null,

    /**
     *
     */
    @SerializedName("avatar_preview")
    var avatarPreview: String? = null,

    /**
     *
     */
    @SerializedName("is_follow")
    val isFollow: Boolean? = false,

    /**
     *
     */
    @SerializedName("occupation")
    var occupation: String? = null,

    /**
     *
     */
    var screenType: String?=DEFAULT_SCREEN,

    /**
     *
     */
    var cached: Int?=DEFAULT_CACHE_VALUE,

    /**
     *
     */
    var favoriteCategories: List<CategoryEntity> = mutableListOf(),

    /**
     *
     */
    var location: LocationEntity? = null

) : BaseModel() {

    fun convertItemForDataSource(
        item: UserEntity,
        isCached: Boolean?,
        screenType: String?
    ): UserEntity {
        isCached?.let { item.cached=CACHED_VALUE }
        screenType?.let { item.screenType=it }
        return item
    }
}



package com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName="location")
class LocationEntity(
    @PrimaryKey(autoGenerate=true)
    @SerializedName("id")
    val id : Int,
    @SerializedName("country")
    var country: String? = "",
    @SerializedName("state")
    var state: String? = "",
    @SerializedName("city")
    var city: String? = ""
)
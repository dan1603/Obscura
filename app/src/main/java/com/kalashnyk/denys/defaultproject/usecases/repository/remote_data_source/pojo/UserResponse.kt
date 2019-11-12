package com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.pojo

import com.google.gson.annotations.SerializedName
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.UserEntity

class UserResponse {
    @SerializedName("records")
    var records: List<UserEntity>? = null
}
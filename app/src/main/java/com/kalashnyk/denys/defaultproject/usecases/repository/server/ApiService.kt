package com.kalashnyk.denys.defaultproject.usecases.repository.server

import com.kalashnyk.denys.defaultproject.usecases.repository.database.entity.UserEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.database.pojo.UserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/android/rest/api-v2.php/records/users")
    fun getUsers(): Single<Response<UserResponse>>

    @GET("/api/android/rest/api-v2.php/records/users/{id}")
    fun getUserById(@Path("id") id: Int): Single<UserEntity>
}

package com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.communicator

import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.MessagesEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.ThemeEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.UserEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.pojo.UserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
//todo create separate ApiService for Users Feed and another part of app
interface ApiService {

    @GET("/api/android/rest/api-v2.php/records/users")
    fun fetchThemes(@Query("screen_type") screenType : String,
                    @Query("last_item_id") lastItemId : String?): Single<Response<List<ThemeEntity>>>

    @GET("/api/android/rest/api-v2.php/records/users")
    fun fetchUsers(@Query("screen_type") screenType : String,
                    @Query("last_item_id") lastItemId : String?): Single<Response<List<UserEntity>>>

    @GET("/api/android/rest/api-v2.php/records/users")
    fun fetchMessages(@Query("screen_type") screenType : String,
                    @Query("last_item_id") lastItemId : String?): Single<Response<List<MessagesEntity>>>

    @GET("/api/android/rest/api-v2.php/records/users")
    fun getUsers(): Single<Response<UserResponse>>

    @GET("/api/android/rest/api-v2.php/records/users/{id}")
    fun getUserById(@Path("id") id: Int): Single<UserEntity>
}

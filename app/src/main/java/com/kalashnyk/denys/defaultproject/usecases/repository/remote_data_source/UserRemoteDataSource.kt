package com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source

import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.UserEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.communicator.ServerCommunicator
import io.reactivex.Single
import retrofit2.Response

//todo create abstract parent for RemoteDataSource

/**
 *
 */
interface UserRemoteDataSource {
    /**
     *
     */
    fun fetchUsers(screenType: String, lastItemId : String?): Single<Response<List<UserEntity>>>

}

/**
 *
 */
class UserRemoteDataSourceImpl(private val serverCommunicator: ServerCommunicator) : UserRemoteDataSource {

    override fun fetchUsers(screenType: String, lastItemId : String?): Single<Response<List<UserEntity>>> =
    serverCommunicator.fetchUsers(screenType=screenType, lastItemId=lastItemId)
}
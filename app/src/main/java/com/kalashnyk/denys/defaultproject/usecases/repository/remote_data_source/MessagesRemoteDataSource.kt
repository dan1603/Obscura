package com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source

import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.MessagesEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.UserEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.communicator.ServerCommunicator
import io.reactivex.Single
import retrofit2.Response

//todo create abstract parent for RemoteDataSource

/**
 *
 */
interface MessagesRemoteDataSource {
    /**
     *
     */
    fun fetchMessages(screenType: String, lastItemId : String?): Single<Response<List<MessagesEntity>>>

}

/**
 *
 */
class MessagesRemoteDataSourceImpl(private val serverCommunicator: ServerCommunicator) : MessagesRemoteDataSource {

    override fun fetchMessages(screenType: String, lastItemId : String?): Single<Response<List<MessagesEntity>>> =
    serverCommunicator.fetchMessages(screenType=screenType, lastItemId=lastItemId)
}
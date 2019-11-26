package com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source

import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.ThemeEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.communicator.ServerCommunicator
import io.reactivex.Single
import retrofit2.Response

/**
 *
 */
interface FeedRemoteDataSource {
    /**
     *
     */
    fun fetchFeed(screenType: String): Single<Response<ThemeEntity>>

    /**
     *
     */
    fun fetchNext(screenType: String, lastItemId: String): Single<Response<ThemeEntity>>
}

/**
 *
 */
class FeedRemoteDataSourceImpl(private val serverCommunicator: ServerCommunicator) : FeedRemoteDataSource {

    override fun fetchFeed(screenType: String): Single<Response<ThemeEntity>> =
        serverCommunicator.fetchThemes(screenType=screenType, lastItemId=null)


    override fun fetchNext(screenType: String, lastItemId: String): Single<Response<ThemeEntity>> =
        serverCommunicator.fetchThemes(screenType=screenType, lastItemId=lastItemId)
}
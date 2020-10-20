package com.kalashnyk.denys.moduleproject.remote_data_source

import com.kalashnyk.denys.moduleproject.data_source.database.entity.ThemeEntity
import com.kalashnyk.denys.moduleproject.remote_data_source.communicator.ServerCommunicator
import io.reactivex.Single
import retrofit2.Response

//todo create abstract parent for RemoteDataSource

/**
 *
 */
interface FeedRemoteDataSource {
    /**
     *
     */
    fun fetchFeed(screenType: String): Single<Response<List<ThemeEntity>>>

    /**
     *
     */
    fun fetchNext(screenType: String, lastItemId: String): Single<Response<List<ThemeEntity>>>
}

/**
 *
 */
class FeedRemoteDataSourceImpl(private val serverCommunicator: ServerCommunicator) : FeedRemoteDataSource {

    override fun fetchFeed(screenType: String): Single<Response<List<ThemeEntity>>> =
        serverCommunicator.fetchThemes(screenType=screenType, lastItemId=null)


    override fun fetchNext(screenType: String, lastItemId: String): Single<Response<List<ThemeEntity>>> =
        serverCommunicator.fetchThemes(screenType=screenType, lastItemId=lastItemId)
}
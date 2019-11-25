package com.kalashnyk.denys.defaultproject.usecases.repository

import androidx.paging.DataSource
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.FeedDataSource
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.AppDatabase
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.FeedRemoteDataSource
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.communicator.ServerCommunicator
import com.kalashnyk.denys.defaultproject.utils.ConverterFactory
import io.reactivex.Completable

/**
 *
 */
interface FeedRepository {

    /**
     *
     */
    fun fetchFeed(type: String): Completable

    /**
     *
     */
    fun fetchNext(type: String, lastItemId: String): Completable

    /**
     *
     */
    fun deleteCachedFeed(filterType: String): Completable

    /**
     *
     */
    fun getCardsFactory(
        type: String,
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel>
}

class FeedRepositoryImpl(
    private val feedRemoteDataSource: FeedRemoteDataSource,
                         private val feedDataSource: FeedDataSource
) : FeedRepository {

    override fun fetchFeed(type: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchNext(type: String, lastItemId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteCachedFeed(filterType: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCardsFactory(
        type: String,
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
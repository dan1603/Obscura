package com.kalashnyk.denys.defaultproject.usecases.repository

import androidx.paging.DataSource
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.FeedDataSource
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.FeedRemoteDataSource
import com.kalashnyk.denys.defaultproject.utils.ConverterFactory
import io.reactivex.Completable
import org.apache.commons.lang3.StringUtils
import java.util.NoSuchElementException

/**
 *
 */
interface FeedRepository {

    /**
     *
     */
    fun fetchFeed(screenType: String): Completable

    /**
     *
     */
    fun fetchNext(screenType: String, lastItemId: String): Completable

    /**
     *
     */
    fun deleteCachedFeed(screenType: String): Completable

    /**
     *
     */
    fun getCardsFactory(
        screenType: String,
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel>
}

/**
 *
 */
class FeedRepositoryImpl(
    private val feedRemoteDataSource: FeedRemoteDataSource,
    private val feedDataSource: FeedDataSource
) : FeedRepository {

    override fun fetchFeed(screenType: String): Completable {
        return feedRemoteDataSource
            .fetchFeed(screenType)
            .flatMapCompletable {  }
//       return Completable.fromAction {
//            var feeds= feedRemoteDataSource
//                .fetchFeed(screenType).blockingGet()
//            if (feeds != null && !feeds!!.isEmpty()) {
//                val isCached=StringUtils.isNotBlank(lastFeedId)
//                feeds=FeedDataUtil.initializeFeeds(feeds, isCached, filterType, null)
//                saveFeeds(feeds, isCached, filterType, null)
//            } else {
//                throw NoSuchElementException()
//            }
//        }
    }

    override fun fetchNext(screenType: String, lastItemId: String): Completable {
        return feedRemoteDataSource
            .fetchNext(screenType, lastItemId)
            .flatMapCompletable {  }
    }

    override fun deleteCachedFeed(screenType: String): Completable=
        Completable.fromAction { feedDataSource.deleteCachedFeed(screenType) }


    override fun getCardsFactory(
        screenType: String,
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel> =
        feedDataSource.getCardsModelsFactory(screenType, modelConverter)

}
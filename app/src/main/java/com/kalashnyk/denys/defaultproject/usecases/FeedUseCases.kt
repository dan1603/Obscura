package com.kalashnyk.denys.defaultproject.usecases

import androidx.paging.DataSource
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.usecases.repository.FeedRepository
import com.kalashnyk.denys.defaultproject.utils.ConverterFactory
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *
 */
interface FeedUseCases {

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
class FeedUseCasesImpl(private val repository: FeedRepository) : FeedUseCases {

    //todo use compositer for subscribeOn and observeOn
    override fun fetchFeed(screenType: String): Completable=
        repository.fetchFeed(screenType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    //todo use compositer for subscribeOn and observeOn
    override fun fetchNext(screenType: String, lastItemId: String): Completable=
        repository.fetchNext(screenType, lastItemId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    //todo use compositer for subscribeOn and observeOn
    override fun deleteCachedFeed(screenType: String): Completable=
        repository.deleteCachedFeed(screenType)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())

    override fun getCardsFactory(
        screenType: String,
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel> =
        repository.getCardsFactory(screenType, modelConverter)
}
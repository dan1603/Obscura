package com.kalashnyk.denys.defaultproject.usecases

import androidx.paging.DataSource
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.usecases.repository.FeedRepository
import com.kalashnyk.denys.defaultproject.utils.ConverterFactory
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

interface FeedUseCases {

    fun fetchFeed(type: String): Completable

    fun fetchNext(type: String, lastItemId: String): Completable

    fun deleteCachedFeed(filterType: String): Completable

    fun getCardsFactory(
        type: String,
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel>
}

/**
 *
 */
class FeedUseCasesImpl(private val repository: FeedRepository) : FeedUseCases {

    //todo use compositer for subscribeOn and observeOn
    override fun fetchFeed(type: String): Completable=
        repository.fetchFeed(type)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())

    //todo use compositer for subscribeOn and observeOn
    override fun fetchNext(type: String, lastItemId: String): Completable=
        repository.fetchNext(type, lastItemId)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())

    //todo use compositer for subscribeOn and observeOn
    override fun deleteCachedFeed(type: String): Completable=
        repository.deleteCachedFeed(type)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())

    override fun getCardsFactory(
        type: String,
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel> =
        repository.getCardsFactory(type, modelConverter)
}
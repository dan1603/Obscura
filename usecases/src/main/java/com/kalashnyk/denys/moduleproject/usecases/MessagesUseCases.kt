package com.kalashnyk.denys.moduleproject.usecases

import androidx.paging.DataSource
import com.kalashnyk.denys.moduleproject.data_source.data.BaseCardModel
import com.kalashnyk.denys.moduleproject.data_source.database.converters.ConverterFactory
import com.kalashnyk.denys.moduleproject.repository.MessagesRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 *
 */
interface MessagesUseCases {

    /**
     *
     */
    fun fetchData(screenType: String): Completable

    /**
     *
     */
    fun fetchNext(screenType: String, lastItemId: String): Completable

    /**
     *
     */
    fun deleteCachedItems(screenType: String): Completable

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
class MessagesUseCasesImpl(private val repository: MessagesRepository) : MessagesUseCases {

    //todo use compositer for subscribeOn and observeOn
    override fun fetchData(screenType: String): Completable=
        repository.fetchMessages(screenType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    //todo use compositer for subscribeOn and observeOn
    override fun fetchNext(screenType: String, lastItemId: String): Completable=
        repository.fetchNext(screenType, lastItemId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    //todo use compositer for subscribeOn and observeOn
    override fun deleteCachedItems(screenType: String): Completable=
        repository.deleteCachedItems(screenType)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())

    override fun getCardsFactory(
        screenType: String,
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel> =
        repository.getCardsFactory(screenType, modelConverter)
}
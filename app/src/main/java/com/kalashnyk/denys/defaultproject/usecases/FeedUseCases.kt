package com.kalashnyk.denys.defaultproject.usecases

import androidx.paging.DataSource
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.usecases.repository.FeedRepository
import com.kalashnyk.denys.defaultproject.utils.ConverterFactory
import io.reactivex.Completable

interface FeedUseCases{

    fun fetchFeed(type: String): Completable

    fun fetchNext(type: String, lastItemId: String): Completable

    fun deleteCachedFeed(filterType: String): Completable

    fun getCardsFactory(
        type: String,
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel>

}

class FeedUseCasesImpl(private val repository: FeedRepository) : FeedUseCases{

    override fun fetchFeed(type: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchNext(filterType: String, lastItemId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteCachedFeed(type: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCardsFactory(
        type: String,
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
package com.kalashnyk.denys.moduleproject.repository

import androidx.paging.DataSource
import com.kalashnyk.denys.moduleproject.data_source.MessagesDataSource
import com.kalashnyk.denys.moduleproject.data_source.data.BaseCardModel
import com.kalashnyk.denys.moduleproject.data_source.database.converters.ConverterFactory
import com.kalashnyk.denys.moduleproject.data_source.database.entity.MessagesEntity
import com.kalashnyk.denys.moduleproject.remote_data_source.MessagesRemoteDataSource
import com.kalashnyk.denys.moduleproject.utils.MocUtil
import io.reactivex.Completable
import io.reactivex.Single

//todo create abstract parent for Repository

/**
 *
 */
interface MessagesRepository {

    /**
     *
     */
    fun fetchMessages(screenType: String): Completable

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
class MessagesRepositoryImpl(
    private val messagesRemoteDataSource: MessagesRemoteDataSource,
    private val messagesDataSource: MessagesDataSource
) : MessagesRepository {

    override fun fetchMessages(screenType: String): Completable {
        return messagesRemoteDataSource
            .fetchMessages(screenType, null)
            // todo remove moc logic and add handling error when api logic implemented
            .doOnError {
                val list: List<MessagesEntity> = MocUtil.mocListMessages()
                list.forEach {
                    it.convertItemForDataSource(item = it, isCached = false, screenType = null)
                }
                saveItems(list, false, screenType)
            }
            .flatMapCompletable {
                Completable.fromAction { }
            }
    }

    override fun fetchNext(screenType: String, lastItemId: String): Completable {
        return messagesRemoteDataSource
            .fetchMessages(screenType, lastItemId)
            // todo remove moc logic and add handling error when api logic implemented
            .flatMap {
                val list: List<MessagesEntity> = MocUtil.mocListMessages()
                list.forEach {
                    it.convertItemForDataSource(item = it, isCached = true, screenType = null)
                }
                Single.just(list)
            }
            .flatMapCompletable {
                Completable.fromAction { saveItems(it, true, screenType) }
            }
    }

    override fun deleteCachedItems(screenType: String): Completable=
        Completable.fromAction { messagesDataSource.deleteCachedItems(screenType) }


    override fun getCardsFactory(
        screenType: String,
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel> =
        messagesDataSource.getCardsModelsFactory(screenType, modelConverter)

    private fun saveItems(
        items: List<MessagesEntity>, isCached: Boolean, typeScreen: String?
    ) {
        if (isCached) {
            messagesDataSource.insert(items)
        } else {
            messagesDataSource.insertAndClearCache(items, typeScreen)
        }
    }

}
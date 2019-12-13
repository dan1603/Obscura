package com.kalashnyk.denys.defaultproject.usecases.repository


import androidx.paging.DataSource
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.UserDataSource
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.UserEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.UserRemoteDataSource
import com.kalashnyk.denys.defaultproject.utils.ConverterFactory
import com.kalashnyk.denys.defaultproject.utils.MocUtil
import io.reactivex.Completable
import io.reactivex.Single

//todo create abstract parent for Repository

/**
 *
 */
interface UserRepository {

    /**
     *
     */
    fun fetchUsers(screenType: String): Completable

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
class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userDataSource: UserDataSource
) : UserRepository {

    override fun fetchUsers(screenType: String): Completable {
        return userRemoteDataSource
            .fetchUsers(screenType, null)
            // todo remove moc logic and add handling error when api logic implemented
            .doOnError {
                val list: List<UserEntity> = MocUtil.mocListUsers()
                list.forEach {
                    it.converItemForDataSource(item = it, isCached = false, screenType = screenType)
                }
                saveItems(list, false, screenType)
            }
            .flatMapCompletable {
                Completable.fromAction { }
            }
    }

    override fun fetchNext(screenType: String, lastItemId: String): Completable {
        return userRemoteDataSource
            .fetchUsers(screenType, lastItemId)
            // todo remove moc logic and add handling error when api logic implemented
            .flatMap {
                val list: List<UserEntity> = MocUtil.mocListUsers()
                list.forEach {
                    it.converItemForDataSource(item = it, isCached = true, screenType = screenType)
                }
                Single.just(list)
            }
            .flatMapCompletable {
                Completable.fromAction { saveItems(it, true, screenType) }
            }
    }

    override fun deleteCachedItems(screenType: String): Completable=
        Completable.fromAction { userDataSource.deleteCachedItems(screenType) }


    override fun getCardsFactory(
        screenType: String,
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel> =
        userDataSource.getCardsModelsFactory(screenType, modelConverter)

    private fun saveItems(
        items: List<UserEntity>, isCached: Boolean, typeScreen: String?
    ) {
        if (isCached) {
            userDataSource.insert(items)
        } else {
            userDataSource.insertAndClearCache(items, typeScreen)
        }
    }

}
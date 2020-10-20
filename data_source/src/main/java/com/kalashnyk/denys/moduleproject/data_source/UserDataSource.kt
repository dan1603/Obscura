package com.kalashnyk.denys.moduleproject.data_source


import androidx.paging.DataSource
import com.kalashnyk.denys.moduleproject.data_source.data.BaseCardModel
import com.kalashnyk.denys.moduleproject.data_source.database.AppDatabase
import com.kalashnyk.denys.moduleproject.data_source.database.converters.ConverterFactory
import com.kalashnyk.denys.moduleproject.data_source.database.entity.UserEntity

//todo create abstract parent for DataSource

/**
 *
 */
interface UserDataSource {

    /**
     *
     */
    fun getCardsModelsFactory(screenType: String, converterFactory: ConverterFactory)
            : DataSource.Factory<Int, BaseCardModel>

    /**
     *
     */
    fun deleteCachedItems(screenType: String)

    /**
     *
     */
    fun insert(items: List<UserEntity>)

    /**
     *
     */
    fun insertAndClearCache(items: List<UserEntity>, typeScreen: String?)
}

/**
 *
 */
class UsersDataSourceImpl(private val database: AppDatabase) : UserDataSource {

    /**
     *
     */
    override fun getCardsModelsFactory(
        screenType: String, converterFactory: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel> {
        return database.userDao().getDataSource(screenType)
            .mapByPage(converterFactory::convert)
    }

    /**
     *
     */
    override fun deleteCachedItems(screenType: String): Unit=
        database.userDao().deleteCachedItems(screenType)

    override fun insert(items: List<UserEntity>) : Unit=
        database.userDao().insert(items)


    override fun insertAndClearCache(items: List<UserEntity>, typeScreen: String?): Unit=
        database.userDao().insertAndClearCache(items, typeScreen)

}
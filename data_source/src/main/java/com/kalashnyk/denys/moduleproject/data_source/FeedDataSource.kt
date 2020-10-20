package com.kalashnyk.denys.moduleproject.data_source

import androidx.paging.DataSource
import com.kalashnyk.denys.moduleproject.data_source.data.BaseCardModel
import com.kalashnyk.denys.moduleproject.data_source.database.AppDatabase
import com.kalashnyk.denys.moduleproject.data_source.database.converters.ConverterFactory
import com.kalashnyk.denys.moduleproject.data_source.database.entity.ThemeEntity

//todo create abstract parent for DataSource

/**
 *
 */
interface FeedDataSource {

    /**
     *
     */
    fun getCardsModelsFactory(screenType: String, converterFactory: ConverterFactory)
            : DataSource.Factory<Int, BaseCardModel>

    /**
     *
     */
    fun deleteCachedFeed(screenType: String)

    /**
     *
     */
    fun insert(feedItems: List<ThemeEntity>)

    /**
     *
     */
    fun receive() : List<ThemeEntity>

    /**
     *
     */
    fun insertAndClearCache(feedItems: List<ThemeEntity>, typeScreen: String?)
}

/**
 *
 */
class FeedDataSourceImpl(private val database: AppDatabase) : FeedDataSource {

    /**
     *
     */
    override fun getCardsModelsFactory(
        screenType: String, converterFactory: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel> {
        return database.themeDao().getDataSource(screenType)
            .mapByPage(converterFactory::convert)
    }

    /**
     *
     */
    override fun deleteCachedFeed(screenType: String): Unit=
        database.themeDao().deleteCachedItems(screenType)

    override fun insert(feedItems: List<ThemeEntity>) : Unit=
        database.themeDao().insert(feedItems)

    override fun receive(): List<ThemeEntity> =
        database.themeDao().queryFeeds()


    override fun insertAndClearCache(feedItems: List<ThemeEntity>, typeScreen: String?): Unit=
        database.themeDao().insertAndClearCache(feedItems, typeScreen)

}
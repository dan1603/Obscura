package com.kalashnyk.denys.moduleproject.data_source.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.kalashnyk.denys.moduleproject.data_source.database.BaseDao
import com.kalashnyk.denys.moduleproject.data_source.database.entity.ThemeEntity

@Dao
interface ThemeDAO : BaseDao<ThemeEntity> {

    @Query("SELECT * FROM themes")
    fun queryFeeds(): List<ThemeEntity>

    @Query("SELECT * FROM themes WHERE id = :id")
    fun queryById(id: Int): ThemeEntity

    @Update
    fun updateAll(listEntities: List<ThemeEntity>)

    @Query("DELETE FROM themes WHERE screenType = :screenType AND cached = 1")
    fun deleteCachedItems(screenType: String)

    @Query("SELECT * FROM themes WHERE screenType = :screenType")
    fun getDataSource(
        screenType: String
    ): DataSource.Factory<Int, ThemeEntity>

    @Query("DELETE FROM themes WHERE cached = 1")
    fun deleteAllCachedItems()

    @Query("DELETE FROM themes WHERE screenType = :screenType")
    fun deleteAllItemsByType(screenType: String)

    @Transaction
    fun insertAndClearCache(
        listEntities: List<ThemeEntity>,
        screenType: String?
    ) {
        screenType?.let {
            deleteAllItemsByType(it)
        }
        insert(listEntities)
    }
}
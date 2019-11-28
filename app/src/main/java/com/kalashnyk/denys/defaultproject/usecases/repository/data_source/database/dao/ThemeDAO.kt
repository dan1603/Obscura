package com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.ThemeEntity

@Dao
interface ThemeDAO {
    @Query("SELECT * FROM themes")
    fun queryFeeds(): List<ThemeEntity>

    @Query("SELECT * FROM themes WHERE id = :id")
    fun queryById(id: Int): ThemeEntity

    @Insert
//        (onConflict=OnConflictStrategy.REPLACE)
    fun insertList(list: List<ThemeEntity>)

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    fun insert(userEntity: List<ThemeEntity>)

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    fun update(userEntity: ThemeEntity)

    @Update
    fun updateAll(list: List<ThemeEntity>)

    @Delete
    fun delete(userEntity: ThemeEntity)

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
        feedItems: List<ThemeEntity>,
        screenType: String?
    ) {
        screenType?.let {
            deleteAllItemsByType(it)
        }
        insert(feedItems)
    }
}
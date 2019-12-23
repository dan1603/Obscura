package com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.BaseDao
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.UserEntity

@Dao
interface UserDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM users")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getById(id: Int): UserEntity

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    fun insertList(listEntities: List<UserEntity>)

    @Update
    fun updateAll(list: List<UserEntity>)

    @Query("SELECT * FROM users WHERE screenType = :screenType")
    fun getDataSource(
        screenType: String
    ): DataSource.Factory<Int, UserEntity>

    @Query("DELETE FROM users WHERE screenType = :screenType AND cached = 1")
    fun deleteCachedItems(screenType : String)

    @Query("DELETE FROM users WHERE screenType = :screenType")
    fun deleteAllItemsByType(screenType: String)

    @Transaction
    fun insertAndClearCache(
        listEntities: List<UserEntity>,
        screenType: String?
    ) {
        screenType?.let {
            deleteAllItemsByType(it)
        }
        insert(listEntities)
    }
}
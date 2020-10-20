package com.kalashnyk.denys.moduleproject.data_source.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.kalashnyk.denys.moduleproject.data_source.database.BaseDao
import com.kalashnyk.denys.moduleproject.data_source.database.entity.CategoryEntity

@Dao
interface CategoryDao : BaseDao<CategoryEntity> {

    @Query("SELECT * FROM category")
    fun query(): List<CategoryEntity>

    @Query("SELECT * FROM category WHERE id = :id")
    fun queryById(id: Int): CategoryEntity
}
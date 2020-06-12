package com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.BaseDao
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.CategoryEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.LocationEntity

@Dao
interface CategoryDao : BaseDao<CategoryEntity> {

    @Query("SELECT * FROM category")
    fun query(): List<CategoryEntity>

    @Query("SELECT * FROM category WHERE id = :id")
    fun queryById(id: Int): CategoryEntity
}
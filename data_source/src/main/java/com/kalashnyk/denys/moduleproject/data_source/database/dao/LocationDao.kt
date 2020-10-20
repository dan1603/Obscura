package com.kalashnyk.denys.moduleproject.data_source.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.kalashnyk.denys.moduleproject.data_source.database.BaseDao
import com.kalashnyk.denys.moduleproject.data_source.database.entity.LocationEntity

@Dao
interface LocationDao : BaseDao<LocationEntity> {

    @Query("SELECT * FROM location")
    fun query(): List<LocationEntity>

    @Query("SELECT * FROM location WHERE id = :id")
    fun queryById(id: Int): LocationEntity
}
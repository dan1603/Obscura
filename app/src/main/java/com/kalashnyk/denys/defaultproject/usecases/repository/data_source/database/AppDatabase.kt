package com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.converters.Converters
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.dao.ThemeDAO
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.dao.UserDao
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.CategoryEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.LocationEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.ThemeEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.UserEntity

@Database(entities = [(UserEntity::class), (ThemeEntity::class), (LocationEntity::class), (CategoryEntity::class)], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun themeDao(): ThemeDAO
}
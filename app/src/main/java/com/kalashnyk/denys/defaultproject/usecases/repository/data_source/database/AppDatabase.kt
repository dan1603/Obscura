package com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.dao.ThemeDAO
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.dao.UserDao
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.UserEntity

@Database(entities = [(UserEntity::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun themeDao(): ThemeDAO
}
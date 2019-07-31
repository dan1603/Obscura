package com.kalashnyk.denys.defaultproject.usecases.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kalashnyk.denys.defaultproject.usecases.repository.database.dao.UserDao
import com.kalashnyk.denys.defaultproject.usecases.repository.database.entity.UserEntity

@Database(entities = [(UserEntity::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
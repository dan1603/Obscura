package com.kalashnyk.denys.defaultproject.usecases.repository.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.kalashnyk.denys.defaultproject.usecases.repository.database.dao.UserDao
import com.kalashnyk.denys.defaultproject.usecases.repository.database.entity.UserEntity

@Database(entities = [(UserEntity::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
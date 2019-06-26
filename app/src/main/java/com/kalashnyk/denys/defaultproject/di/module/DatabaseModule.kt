package com.kalashnyk.denys.defaultproject.di.module

import com.kalashnyk.denys.defaultproject.usecases.repository.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val appDatabase: AppDatabase) {
    @Provides
    internal fun providesRoomDatabase(): AppDatabase {
        return appDatabase
    }
}
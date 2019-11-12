package com.kalashnyk.denys.defaultproject.di.module

import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.FeedDataSource
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.FeedDataSourceImpl
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val appDatabase: AppDatabase) {
    @Provides
    internal fun providesAppDatabase(): AppDatabase {
        return appDatabase
    }

    @Provides
    internal fun providesFeedDataSource(): FeedDataSource {
        return FeedDataSourceImpl(appDatabase)
    }
}
package com.kalashnyk.denys.moduleproject.data_source.di

import com.kalashnyk.denys.moduleproject.data_source.*
import com.kalashnyk.denys.moduleproject.data_source.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val appDatabase: AppDatabase) {
    @Provides
    internal fun providesAppDatabase(): AppDatabase {
        return appDatabase
    }

    @Provides
    internal fun providesFeedDataSource(appDatabase: AppDatabase): FeedDataSource {
        return FeedDataSourceImpl(appDatabase)
    }

    @Provides
    internal fun providesUsersDataSource(appDatabase: AppDatabase): UserDataSource {
        return UsersDataSourceImpl(appDatabase)
    }

    @Provides
    internal fun providesMessagesDataSource(appDatabase: AppDatabase): MessagesDataSource {
        return MessagesDataSourceImpl(appDatabase)
    }
}
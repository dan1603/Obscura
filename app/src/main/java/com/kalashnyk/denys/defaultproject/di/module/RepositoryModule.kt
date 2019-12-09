package com.kalashnyk.denys.defaultproject.di.module

import com.kalashnyk.denys.defaultproject.di.scope.RepositoryScope
import com.kalashnyk.denys.defaultproject.usecases.repository.*
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.FeedDataSource
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.UserDataSource
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.AppDatabase
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.FeedRemoteDataSource
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.UserRemoteDataSource
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.communicator.ServerCommunicator
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @RepositoryScope
    @Provides
    internal fun providesRepository(communicator: ServerCommunicator, database: AppDatabase): AppRepository {
        return AppRepository(communicator, database)
    }

    @RepositoryScope
    @Provides
    internal fun providesFeedRepository(feedRemoteDataSource: FeedRemoteDataSource, feedDataSource: FeedDataSource): FeedRepository {
        return FeedRepositoryImpl(feedRemoteDataSource, feedDataSource)
    }

    @RepositoryScope
    @Provides
    internal fun providesUserRepository(userRemoteDataSource: UserRemoteDataSource, userDataSource: UserDataSource): UserRepository {
        return UserRepositoryImpl(userRemoteDataSource, userDataSource)
    }
}
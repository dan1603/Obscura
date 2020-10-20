package com.kalashnyk.denys.moduleproject.repository.di

import com.kalashnyk.denys.moduleproject.data_source.*
import com.kalashnyk.denys.moduleproject.data_source.database.AppDatabase
import com.kalashnyk.denys.moduleproject.remote_data_source.*
import com.kalashnyk.denys.moduleproject.remote_data_source.communicator.ServerCommunicator
import com.kalashnyk.denys.moduleproject.repository.*
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

    @RepositoryScope
    @Provides
    internal fun providesMessagesRepository(messagesRemoteDataSource: MessagesRemoteDataSource, messagesDataSource: MessagesDataSource): MessagesRepository {
        return MessagesRepositoryImpl(messagesRemoteDataSource, messagesDataSource)
    }
}
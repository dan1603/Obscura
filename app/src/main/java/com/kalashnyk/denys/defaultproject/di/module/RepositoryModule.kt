package com.kalashnyk.denys.defaultproject.di.module

import com.kalashnyk.denys.defaultproject.di.scope.RepositoryScope
import com.kalashnyk.denys.defaultproject.usecases.repository.AppRepository
import com.kalashnyk.denys.defaultproject.usecases.repository.database.AppDatabase
import com.kalashnyk.denys.defaultproject.usecases.repository.server.ServerCommunicator
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @RepositoryScope
    @Provides
    internal fun providesRepository(communicator: ServerCommunicator, database: AppDatabase): AppRepository {
        return AppRepository(communicator, database)
    }
}
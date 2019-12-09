package com.kalashnyk.denys.defaultproject.di.module

import com.kalashnyk.denys.defaultproject.di.scope.InteractorScope
import com.kalashnyk.denys.defaultproject.usecases.*
import com.kalashnyk.denys.defaultproject.usecases.repository.AppRepository
import com.kalashnyk.denys.defaultproject.usecases.repository.FeedRepository
import com.kalashnyk.denys.defaultproject.usecases.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @InteractorScope
    @Provides
    internal fun providesInteractor(repository: AppRepository): Interactor {
        return Interactor(repository)
    }

    @InteractorScope
    @Provides
    internal fun providesFeedUseCases(repository: FeedRepository): FeedUseCases {
        return FeedUseCasesImpl(repository)
    }

    @InteractorScope
    @Provides
    internal fun providesUserUseCases(repository: UserRepository): UserUseCases {
        return UserUseCasesImpl(repository)
    }
}
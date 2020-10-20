package com.kalashnyk.denys.defaultproject.di.module

import com.kalashnyk.denys.moduleproject.repository.AppRepository
import com.kalashnyk.denys.moduleproject.repository.FeedRepository
import com.kalashnyk.denys.moduleproject.repository.MessagesRepository
import com.kalashnyk.denys.moduleproject.repository.UserRepository
import com.kalashnyk.denys.defaultproject.di.scope.InteractorScope
import com.kalashnyk.denys.moduleproject.usecases.*
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

    @InteractorScope
    @Provides
    internal fun providesMessagesUseCases(repository: MessagesRepository): MessagesUseCases {
        return MessagesUseCasesImpl(repository)
    }
}
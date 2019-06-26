package com.kalashnyk.denys.defaultproject.di.module

import com.kalashnyk.denys.defaultproject.di.scope.InteractorScope
import com.kalashnyk.denys.defaultproject.usecases.Interactor
import com.kalashnyk.denys.defaultproject.usecases.repository.AppRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {
    @InteractorScope
    @Provides
    internal fun providesInteractor(repository: AppRepository): Interactor {
        return Interactor(repository)
    }
}
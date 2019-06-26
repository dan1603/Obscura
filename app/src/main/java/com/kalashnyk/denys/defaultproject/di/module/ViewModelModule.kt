package com.kalashnyk.denys.defaultproject.di.module

import android.app.Application
import com.kalashnyk.denys.defaultproject.App
import com.kalashnyk.denys.defaultproject.di.scope.ViewModelScope
import com.kalashnyk.denys.defaultproject.domain.AllUsersViewModel
import com.kalashnyk.denys.defaultproject.domain.SingleUserViewModel
import com.kalashnyk.denys.defaultproject.usecases.Interactor
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(app: App) {

    internal var app: Application = app

    @ViewModelScope
    @Provides
    internal fun providesAllUserViewModel(interactor: Interactor): AllUsersViewModel {
        return AllUsersViewModel(app, interactor)
    }

    @ViewModelScope
    @Provides
    internal fun providesSingleUserViewModel(interactor: Interactor): SingleUserViewModel {
        return SingleUserViewModel(app, interactor)
    }
}
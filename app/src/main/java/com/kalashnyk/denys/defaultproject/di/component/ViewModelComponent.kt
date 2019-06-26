package com.kalashnyk.denys.defaultproject.di.component

import com.kalashnyk.denys.defaultproject.di.module.ViewModelModule
import com.kalashnyk.denys.defaultproject.di.scope.ViewModelScope
import com.kalashnyk.denys.defaultproject.presentation.activities.detail.DetailActivity
import com.kalashnyk.denys.defaultproject.presentation.activities.main.MainActivity
import dagger.Component

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [InteractorComponent::class])
interface ViewModelComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
}
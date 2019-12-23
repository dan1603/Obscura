package com.kalashnyk.denys.defaultproject.di.component

import com.kalashnyk.denys.defaultproject.di.module.ViewModelModule
import com.kalashnyk.denys.defaultproject.di.scope.ViewModelScope
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.AuthActivity
import com.kalashnyk.denys.defaultproject.presentation.activities.detail.DetailActivity
import com.kalashnyk.denys.defaultproject.presentation.activities.main.MainActivity
import com.kalashnyk.denys.defaultproject.presentation.activities.splash.SplashActivity
import com.kalashnyk.denys.defaultproject.presentation.activities.welcome.WelcomeActivity
import com.kalashnyk.denys.defaultproject.presentation.fragments.list_users.ListUsersFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.list_themes.ListThemesFragment
import dagger.Component

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [InteractorComponent::class])
interface ViewModelComponent {
    fun inject(activity: SplashActivity)
    fun inject(activity: WelcomeActivity)
    fun inject(activity: AuthActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
    fun inject(fragment: ListThemesFragment)
    fun inject(fragment: ListUsersFragment)
}
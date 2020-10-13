package com.kalashnyk.denys.defaultproject.di.component

import com.kalashnyk.denys.defaultproject.di.module.ViewModelModule
import com.kalashnyk.denys.defaultproject.di.scope.ViewModelScope
import com.kalashnyk.denys.defaultproject.presentation.activities.auth.AuthActivity
import com.kalashnyk.denys.defaultproject.presentation.activities.detail.*
import com.kalashnyk.denys.defaultproject.presentation.activities.location.LocationChooserActivity
import com.kalashnyk.denys.defaultproject.presentation.activities.main.MainActivity
import com.kalashnyk.denys.defaultproject.presentation.activities.splash.SplashActivity
import com.kalashnyk.denys.defaultproject.presentation.activities.welcome.WelcomeActivity
import com.kalashnyk.denys.defaultproject.presentation.fragments.list_themes.ListThemesFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.list_users.ListUsersFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.list_messages.ListMessagesFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.profile.ProfileFragment
import dagger.Component

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [InteractorComponent::class])
interface ViewModelComponent {
    fun inject(activity: SplashActivity)
    fun inject(activity: WelcomeActivity)
    fun inject(activity: AuthActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
    fun inject(activity: ConversationActivity)
    fun inject(activity: DetailListActivity)
    fun inject(activity: DetailFeedActivity)
    fun inject(activity: LocationChooserActivity)
    fun inject(fragment: ListThemesFragment)
    fun inject(fragment: ListUsersFragment)
    fun inject(fragment: ListMessagesFragment)
    fun inject(fragment: ProfileFragment)
}
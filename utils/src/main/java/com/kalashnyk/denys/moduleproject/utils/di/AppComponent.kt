package com.kalashnyk.denys.moduleproject.utils.di

import com.google.gson.Gson
import com.kalashnyk.denys.moduleproject.utils.ApplicationUtils
import com.kalashnyk.denys.moduleproject.utils.preference.PreferencesManager
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    val applicationUtils: ApplicationUtils
    val preferencesManager: PreferencesManager
    val gson: Gson
}
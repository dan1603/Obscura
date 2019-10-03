package com.kalashnyk.denys.defaultproject.di.component

import com.google.gson.Gson
import com.kalashnyk.denys.defaultproject.di.module.AppModule
import com.kalashnyk.denys.defaultproject.di.scope.AppScope
import com.kalashnyk.denys.defaultproject.utils.ApplicationUtils
import com.kalashnyk.denys.defaultproject.utils.preference.PreferencesManager
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    val applicationUtils: ApplicationUtils
    val preferencesManager: PreferencesManager
    val gson: Gson
}
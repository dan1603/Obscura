package com.kalashnyk.denys.defaultproject.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.kalashnyk.denys.defaultproject.di.scope.AppScope
import com.kalashnyk.denys.defaultproject.utils.ApplicationConstants
import com.kalashnyk.denys.defaultproject.utils.ApplicationUtils
import com.kalashnyk.denys.defaultproject.utils.preference.PreferencesManager
import dagger.Module
import dagger.Provides

/**
 *
 */
@Module
class AppModule(private val app : Application) {

    /**
     *
     */
    @Provides
    @AppScope
    fun provideApplication(): Application {
        return app
    }

    /**
     *
     */
    @Provides
    @AppScope
    fun provideGson(): Gson {
        return Gson()
    }

    /**
     *
     */
    @Provides
    @AppScope
    fun providePreferencesManager(app : Application): PreferencesManager {
        return PreferencesManager(app.getSharedPreferences(ApplicationConstants.PROJECT_PREFERENCE, Context.MODE_PRIVATE ))
    }


}
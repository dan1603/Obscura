package com.kalashnyk.denys.moduleproject.utils.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.kalashnyk.denys.moduleproject.utils.ApplicationUtils
import com.kalashnyk.denys.moduleproject.utils.PROJECT_PREFERENCE
import com.kalashnyk.denys.moduleproject.utils.preference.PreferencesManager
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
        return PreferencesManager(app.getSharedPreferences(PROJECT_PREFERENCE, Context.MODE_PRIVATE ))
    }


    @Provides
    @AppScope
    fun provideApplicationUtils(app : Application): ApplicationUtils {
        return ApplicationUtils(app)
    }

}
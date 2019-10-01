package com.kalashnyk.denys.defaultproject.di.module

import android.app.Application
import com.kalashnyk.denys.defaultproject.di.scope.AppScope
import com.kalashnyk.denys.defaultproject.utils.ApplicationUtils
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
    fun provideApplicationUtils(app : Application): ApplicationUtils {
        return ApplicationUtils(app)
    }
}
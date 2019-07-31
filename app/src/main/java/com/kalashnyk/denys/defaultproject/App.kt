package com.kalashnyk.denys.defaultproject

import androidx.room.Room
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.kalashnyk.denys.defaultproject.di.component.*
import com.kalashnyk.denys.defaultproject.di.module.*
import com.kalashnyk.denys.defaultproject.usecases.repository.database.AppDatabase
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

class App: MultiDexApplication() {

    private var viewModelComponent: ViewModelComponent? = null
    private var database: AppDatabase? = null
    private var refWatcher: RefWatcher? = null

    init {
        applicationInstance = this
    }

    companion object {
        private lateinit var applicationInstance: App
        private val LEAK_CANARY_ENABLED = true

        @JvmStatic
        fun getAppContext(): Context {
            return applicationInstance.applicationContext
        }

        fun get(): App {
            return applicationInstance.applicationContext as App
        }

        fun getRefWatcher(): RefWatcher {
            return get().refWatcher!!
        }

        fun isLeakCanaryEnabled(): Boolean {
            return (BuildConfig.DEBUG && LEAK_CANARY_ENABLED
                    && BuildConfig.APPLICATION_ID.equals(
                "com.kalashnyk.denys.defaultproject"
            ))
        }
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        initRoom()
        initDagger()
    }

    private fun initRoom() {
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    private fun initDagger() {
        val apiComponent = DaggerApiComponent.builder()
            .apiModule(ApiModule())
            .build()

        val databaseComponent = DaggerDatabaseComponent.builder()
            .databaseModule(DatabaseModule(this!!.database!!))
            .build()

        val repositoryComponent = DaggerRepositoryComponent.builder()
            .apiComponent(apiComponent)
            .databaseComponent(databaseComponent)
            .repositoryModule(RepositoryModule())
            .build()

        val interactorComponent = DaggerInteractorComponent.builder()
            .repositoryComponent(repositoryComponent)
            .interactorModule(InteractorModule())
            .build()

        viewModelComponent = DaggerViewModelComponent.builder()
            .interactorComponent(interactorComponent)
            .viewModelModule(ViewModelModule(this))
            .build()
    }

    fun getViewModelComponent(): ViewModelComponent {
        return this.viewModelComponent!!
    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        if (isLeakCanaryEnabled()) {
            refWatcher = LeakCanary.install(this)
        }
    }
}
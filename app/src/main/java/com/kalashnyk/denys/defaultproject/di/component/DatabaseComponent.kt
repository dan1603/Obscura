package com.kalashnyk.denys.defaultproject.di.component

import com.kalashnyk.denys.defaultproject.di.module.DatabaseModule
import com.kalashnyk.denys.defaultproject.usecases.repository.database.AppDatabase
import dagger.Component

@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    val database: AppDatabase
}

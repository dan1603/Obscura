package com.kalashnyk.denys.defaultproject.di.component

import com.kalashnyk.denys.defaultproject.di.module.DatabaseModule
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.FeedDataSource
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.UserDataSource
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.AppDatabase
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.FeedRemoteDataSource
import dagger.Component

@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    val database: AppDatabase
    val feedDataSource: FeedDataSource
    val userDataSource: UserDataSource
}

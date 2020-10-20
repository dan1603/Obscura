package com.kalashnyk.denys.moduleproject.data_source.di

import com.kalashnyk.denys.moduleproject.data_source.*
import com.kalashnyk.denys.moduleproject.data_source.database.AppDatabase
import dagger.Component

@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    val database: AppDatabase
    val feedDataSource: FeedDataSource
    val userDataSource: UserDataSource
    val messagesDataSource: MessagesDataSource
}

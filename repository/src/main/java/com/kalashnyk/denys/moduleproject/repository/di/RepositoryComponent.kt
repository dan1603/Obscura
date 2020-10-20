package com.kalashnyk.denys.moduleproject.repository.di

import com.kalashnyk.denys.moduleproject.repository.AppRepository
import com.kalashnyk.denys.moduleproject.repository.FeedRepository
import com.kalashnyk.denys.moduleproject.repository.MessagesRepository
import com.kalashnyk.denys.moduleproject.repository.UserRepository
import com.kalashnyk.denys.moduleproject.data_source.di.DatabaseComponent
import com.kalashnyk.denys.moduleproject.remote_data_source.di.ApiComponent
import dagger.Component

@RepositoryScope
@Component(modules = [RepositoryModule::class], dependencies = [ApiComponent::class, DatabaseComponent::class])
interface RepositoryComponent {
    val repository: AppRepository
    val feedRepository: FeedRepository
    val userRepository: UserRepository
    val messagesRepository: MessagesRepository
}
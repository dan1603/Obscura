package com.kalashnyk.denys.defaultproject.di.component

import com.kalashnyk.denys.defaultproject.di.module.RepositoryModule
import com.kalashnyk.denys.defaultproject.di.scope.RepositoryScope
import com.kalashnyk.denys.defaultproject.usecases.repository.AppRepository
import com.kalashnyk.denys.defaultproject.usecases.repository.FeedRepository
import com.kalashnyk.denys.defaultproject.usecases.repository.MessagesRepository
import com.kalashnyk.denys.defaultproject.usecases.repository.UserRepository
import dagger.Component

@RepositoryScope
@Component(modules = [RepositoryModule::class], dependencies = [ApiComponent::class, DatabaseComponent::class])
interface RepositoryComponent {
    val repository: AppRepository
    val feedRepository: FeedRepository
    val userRepository: UserRepository
    val messagesRepository: MessagesRepository
}
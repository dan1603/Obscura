package com.kalashnyk.denys.defaultproject.di.component

import com.kalashnyk.denys.defaultproject.di.module.InteractorModule
import com.kalashnyk.denys.defaultproject.di.scope.InteractorScope
import com.kalashnyk.denys.moduleproject.repository.di.RepositoryComponent
import com.kalashnyk.denys.moduleproject.usecases.FeedUseCases
import com.kalashnyk.denys.moduleproject.usecases.Interactor
import com.kalashnyk.denys.moduleproject.usecases.MessagesUseCases
import com.kalashnyk.denys.moduleproject.usecases.UserUseCases
import dagger.Component

@InteractorScope
@Component(modules = [InteractorModule::class], dependencies = [RepositoryComponent::class])
interface InteractorComponent {
    val interactor: Interactor
    val feedUseCases : FeedUseCases
    val userUseCases : UserUseCases
    val messagesUseCases : MessagesUseCases
}
package com.kalashnyk.denys.defaultproject.di.component

import com.kalashnyk.denys.defaultproject.di.module.InteractorModule
import com.kalashnyk.denys.defaultproject.di.scope.InteractorScope
import com.kalashnyk.denys.defaultproject.usecases.FeedUseCases
import com.kalashnyk.denys.defaultproject.usecases.Interactor
import com.kalashnyk.denys.defaultproject.usecases.MessagesUseCases
import com.kalashnyk.denys.defaultproject.usecases.UserUseCases
import dagger.Component

@InteractorScope
@Component(modules = [InteractorModule::class], dependencies = [RepositoryComponent::class])
interface InteractorComponent {
    val interactor: Interactor
    val feedUseCases : FeedUseCases
    val userUseCases : UserUseCases
    val messagesUseCases : MessagesUseCases
}
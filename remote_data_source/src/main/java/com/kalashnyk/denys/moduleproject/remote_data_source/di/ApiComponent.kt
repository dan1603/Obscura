package com.kalashnyk.denys.moduleproject.remote_data_source.di

import com.kalashnyk.denys.moduleproject.remote_data_source.*
import com.kalashnyk.denys.moduleproject.remote_data_source.communicator.ServerCommunicator
import com.kalashnyk.denys.moduleproject.utils.di.AppComponent
import dagger.Component

@ApiScope
@Component(modules = [ApiModule::class], dependencies = [AppComponent::class])
interface ApiComponent {
    val serverCommunicator: ServerCommunicator
    val feedRemoteDataSource: FeedRemoteDataSource
    val userRemoteDataSource: UserRemoteDataSource
    val messagesRemoteDataSource: MessagesRemoteDataSource
}

package com.kalashnyk.denys.defaultproject.di.component

import com.kalashnyk.denys.defaultproject.di.module.ApiModule
import com.kalashnyk.denys.defaultproject.di.scope.ApiScope
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.FeedRemoteDataSource
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.FeedRemoteDataSourceImpl
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.UserRemoteDataSource
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.communicator.ServerCommunicator
import dagger.Component

@ApiScope
@Component(modules = [ApiModule::class], dependencies = [AppComponent::class])
interface ApiComponent {
    val serverCommunicator: ServerCommunicator
    val feedRemoteDataSource: FeedRemoteDataSource
    val userRemoteDataSource: UserRemoteDataSource
}

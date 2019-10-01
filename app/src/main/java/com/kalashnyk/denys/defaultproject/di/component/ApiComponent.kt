package com.kalashnyk.denys.defaultproject.di.component

import com.kalashnyk.denys.defaultproject.di.module.ApiModule
import com.kalashnyk.denys.defaultproject.di.scope.ApiScope
import com.kalashnyk.denys.defaultproject.usecases.repository.server.ServerCommunicator
import dagger.Component

@ApiScope
@Component(modules = [ApiModule::class], dependencies = [AppComponent::class])
interface ApiComponent {
    val serverCommunicator: ServerCommunicator
}

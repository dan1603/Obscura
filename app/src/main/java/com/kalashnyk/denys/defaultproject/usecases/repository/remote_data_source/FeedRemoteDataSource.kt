package com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source

import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.communicator.ServerCommunicator

interface FeedRemoteDataSource {

}

class FeedRemoteDataSourceImpl(private val serverCommunicator: ServerCommunicator) : FeedRemoteDataSource {
}
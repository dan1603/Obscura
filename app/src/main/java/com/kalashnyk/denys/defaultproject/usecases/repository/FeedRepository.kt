package com.kalashnyk.denys.defaultproject.usecases.repository

import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.FeedDataSource
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.AppDatabase
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.FeedRemoteDataSource
import com.kalashnyk.denys.defaultproject.usecases.repository.remote_data_source.communicator.ServerCommunicator

interface FeedRepository {

}

class FeedRepositoryImpl(
    private val feedRemoteDataSource: FeedRemoteDataSource,
                         private val feedDataSource: FeedDataSource
) : FeedRepository {

}
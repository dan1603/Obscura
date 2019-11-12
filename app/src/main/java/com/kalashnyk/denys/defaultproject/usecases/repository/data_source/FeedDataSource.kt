package com.kalashnyk.denys.defaultproject.usecases.repository.data_source

import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.AppDatabase

interface FeedDataSource {

}

class FeedDataSourceImpl( private val database: AppDatabase) : FeedDataSource {

}
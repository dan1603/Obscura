package com.kalashnyk.denys.defaultproject.usecases

import com.kalashnyk.denys.defaultproject.usecases.repository.FeedRepository

interface FeedUseCases{

}

class FeedUseCasesImpl(private val repository: FeedRepository) : FeedUseCases{

}
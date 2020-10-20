package com.kalashnyk.denys.moduleproject.usecases

import com.kalashnyk.denys.moduleproject.data_source.database.entity.UserEntity
import com.kalashnyk.denys.moduleproject.repository.AppRepository
import io.reactivex.Single

class Interactor(private val repository: AppRepository) {

    fun getAll(): Single<List<UserEntity>>? {
        return repository.getAll()
    }

    fun getUser(id: Int): Single<UserEntity> {
        return repository.getUser(id)
    }

    fun getFeed(): Single<List<UserEntity>>? {
        return repository.getAll()
    }
}
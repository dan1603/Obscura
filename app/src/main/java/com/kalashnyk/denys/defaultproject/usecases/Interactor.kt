package com.kalashnyk.denys.defaultproject.usecases

import com.kalashnyk.denys.defaultproject.usecases.repository.AppRepository
import com.kalashnyk.denys.defaultproject.usecases.repository.database.entity.UserEntity
import io.reactivex.Single

class Interactor(private val repository: AppRepository) {

    fun getAll(): Single<List<UserEntity>>? {
        return repository.getAll()
    }

    fun getUser(id: Int): Single<UserEntity> {
        return repository.getUser(id)
    }
}
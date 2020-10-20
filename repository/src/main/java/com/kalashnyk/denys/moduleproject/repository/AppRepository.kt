package com.kalashnyk.denys.moduleproject.repository

import com.kalashnyk.denys.moduleproject.data_source.database.AppDatabase
import com.kalashnyk.denys.moduleproject.data_source.database.entity.UserEntity
import com.kalashnyk.denys.moduleproject.remote_data_source.communicator.ServerCommunicator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppRepository(private val serverCommunicator: ServerCommunicator, private val mainDatabase: AppDatabase) {

    fun getAll(): Single<List<UserEntity>>? {
        return serverCommunicator.getAllUsers()
            .flatMap { list ->
                mainDatabase.userDao().insertList(list.body()?.records!!)
                Single.just(mainDatabase.userDao().getAll())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getUser(id: Int): Single<UserEntity> {
        return serverCommunicator.getUser(id)
            .map {
                val user = mainDatabase.userDao().getById(id)
                user
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }
}
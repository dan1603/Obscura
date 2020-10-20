package com.kalashnyk.denys.moduleproject.remote_data_source.communicator

import android.util.Log
import com.kalashnyk.denys.moduleproject.data_source.database.entity.*
import com.kalashnyk.denys.moduleproject.remote_data_source.pojo.UserResponse
import com.kalashnyk.denys.moduleproject.utils.*
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.concurrent.TimeUnit

//todo create separate Communicator for Users Feed and another part of app

class ServerCommunicator(private val mService: ApiService) {

    fun fetchThemes(screenType: String, lastItemId: String?): Single<Response<List<ThemeEntity>>> {
        return mService.fetchThemes(screenType, lastItemId)
            .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message) }
    }

    fun fetchUsers(screenType: String, lastItemId: String?): Single<Response<List<UserEntity>>> {
        return mService.fetchUsers(screenType, lastItemId)
            .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message) }
    }

    fun fetchMessages(screenType: String, lastItemId: String?): Single<Response<List<MessagesEntity>>> {
        return mService.fetchMessages(screenType, lastItemId)
            .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message) }
    }

    fun getAllUsers(): Single<Response<UserResponse>> {
        return mService.getUsers()
            .compose(singleTransformer())
            .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message) }
    }

    fun getUser(id: Int): Single<UserEntity> {
        return mService.getUserById(id).compose(singleTransformer())
    }

    private fun <T> singleTransformer(
        subscribeOn: Scheduler,
        observeOn: Scheduler
    ): SingleTransformer<T, T> = SingleTransformer {
        it.subscribeOn(subscribeOn)
            .observeOn(observeOn)
            .timeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .retry(DEFAULT_RETRY_ATTEMPTS)
    }

    private fun <T> singleTransformer(): SingleTransformer<T, T> = SingleTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .retry(DEFAULT_RETRY_ATTEMPTS)
    }

    private fun <T> observableTransformer(): ObservableTransformer<T, T> = ObservableTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .retry(DEFAULT_RETRY_ATTEMPTS)
    }
}

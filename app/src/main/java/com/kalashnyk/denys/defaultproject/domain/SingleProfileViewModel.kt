package com.kalashnyk.denys.defaultproject.domain

import androidx.lifecycle.MutableLiveData
import com.kalashnyk.denys.defaultproject.presentation.fragments.profile.flow.ProfileModel
import com.kalashnyk.denys.defaultproject.usecases.UserUseCases

/**
 *
 */
class SingleProfileViewModel(private val userUseCases: UserUseCases) : BaseViewModel() {
    /**
     *
     */
    val userModel = MutableLiveData<ProfileModel>()

    /**
     *
     */
    fun initUser(userId: Int){
        compositeDisposable.add(userUseCases.singleUser(userId)
            .subscribe({
                userModel.postValue(ProfileModel(it))
            }, {

            }))
    }
}
package com.kalashnyk.denys.defaultproject.domain

import com.kalashnyk.denys.defaultproject.usecases.FeedUseCases
import com.kalashnyk.denys.defaultproject.utils.ConverterFactory

class FeedViewModel(private val feedUseCases: FeedUseCases) : BaseFeedViewModel() {

    init {
        initPagedConfig()
    }

    fun initLiveData(type: String)=initPagedListLiveData(feedUseCases.getCardsFactory(type, ConverterFactory()))


    fun getPagedList(type: String)=listCards

    override fun fetchData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rangeData(itemId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

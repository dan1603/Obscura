package com.kalashnyk.denys.defaultproject.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.presentation.base.ItemsLoadListener
import com.kalashnyk.denys.defaultproject.usecases.UserUseCases
import com.kalashnyk.denys.defaultproject.utils.ConverterFactory
import java.util.NoSuchElementException

class UserViewModel(private val userUseCases: UserUseCases) : BasePagingViewModel() {
    init {
        initPagedConfig()
    }

    /**
     *
     */
    fun initLiveData(type: String, listener: ItemsLoadListener<PagedList<BaseCardModel>>) {
        typeFeed = type
        itemLoadedListener = listener
        initPagedListLiveData(userUseCases.getCardsFactory(type, ConverterFactory()))
    }


    /**
     *
     */
    fun getPagedList(): LiveData<PagedList<BaseCardModel>> = listCards

    override fun fetchData(typeFeed : String) {
        compositeDisposable.add(userUseCases.fetchData(typeFeed)
            .subscribe({ setRefreshing(false) }, { throwable ->
                if (throwable is NoSuchElementException) {
                    itemLoadedListener.onItemsLoaded(null)
                } else {
                    throwable.message?.let { itemLoadedListener.onLoadError(it) }
                }
                setRefreshing(false)
            })
        )
    }

    override fun rangeData(typeFeed: String, itemId: String) {
        setLoading(true)
        compositeDisposable.add(userUseCases.fetchNext(typeFeed, itemId)
            .subscribe({ setLoading(false) },
                { setLoading(false) }
            )
        )
    }

    override fun clearCachedItems(screenType : String) {
        userUseCases.deleteCachedItems(screenType)
    }
}
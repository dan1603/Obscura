package com.kalashnyk.denys.defaultproject.domain

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.presentation.base.ItemsLoadListener
import com.kalashnyk.denys.defaultproject.utils.CONTENT_PAGE_SIZE
import com.kalashnyk.denys.defaultproject.utils.DEFAULT_INITIAL_LOADED_KEY

/**
 *
 */
abstract class BasePagingViewModel : BaseViewModel() {

    /**
     *
     */
    protected lateinit var listCards: LiveData<PagedList<BaseCardModel>>
    protected var typeFeed: String?=""
    protected lateinit var itemLoadedListener: ItemsLoadListener<PagedList<BaseCardModel>>

    private val refreshing=ObservableBoolean()
    private val loading=ObservableBoolean()

    private lateinit var pagedListConfiguration: PagedList.Config
    private var lastItemId: String?=""

    /**
     *
     */
    fun initPagedConfig() {
        pagedListConfiguration=PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(CONTENT_PAGE_SIZE)
            .setPrefetchDistance(CONTENT_PAGE_SIZE / 2)
            .build()
    }

    /**
     *
     */
    fun initPagedListLiveData(factory: DataSource.Factory<Int, BaseCardModel>) {
        listCards=initPagedList(factory, DEFAULT_INITIAL_LOADED_KEY)
    }

    private fun initPagedList(
        factory: DataSource.Factory<Int, BaseCardModel>,
        initialLoadKey: Int
    ): LiveData<PagedList<BaseCardModel>> {
        setLoading(false)
        return LivePagedListBuilder(factory, pagedListConfiguration)
            .setBoundaryCallback(object : PagedList.BoundaryCallback<BaseCardModel>() {
                override fun onZeroItemsLoaded() {
                    super.onZeroItemsLoaded()
                }

                override fun onItemAtFrontLoaded(itemAtFront: BaseCardModel) {
                    super.onItemAtFrontLoaded(itemAtFront)
                }

                override fun onItemAtEndLoaded(itemAtEnd: BaseCardModel) {
                    super.onItemAtEndLoaded(itemAtEnd)
                    // todo add after remove moc logic
//                    lastItemId?.let {
//                        if(it != itemAtEnd.getCardId()){
//                            typeFeed.apply {
//                                rangeData(it, itemAtEnd.getCardId())
//                                lastItemId=itemAtEnd.getCardId()
//                            }
//                        }
//                    }
                    // todo remove after remove moc logic
                   lastItemId?.let {
                            typeFeed.apply {
                                rangeData(it, itemAtEnd.getCardId())
                                lastItemId=itemAtEnd.getCardId()
                            }
                    }
                }
            })
            .setInitialLoadKey(initialLoadKey)
            .build()
    }

    fun getRefreshing(): ObservableBoolean {
        return refreshing
    }

    fun setRefreshing(isRefreshing: Boolean) {
        refreshing.set(isRefreshing)
    }

    fun isLoading(): ObservableBoolean {
        return loading
    }

    protected fun setLoading(isLoading: Boolean) {
        loading.set(isLoading)
    }

    abstract fun fetchData(typeFeed: String)

    abstract fun rangeData(typeFeed: String, itemId: String)

    abstract fun clearCachedItems(screenType: String)
}
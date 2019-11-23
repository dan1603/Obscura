package com.kalashnyk.denys.defaultproject.domain

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.utils.CONTENT_PAGE_SIZE
import com.kalashnyk.denys.defaultproject.utils.DEFAULT_INITIAL_LOADED_KEY
import org.apache.commons.lang3.StringUtils

abstract class BaseFeedViewModel : BaseViewModel() {

    protected lateinit var listCards: LiveData<PagedList<BaseCardModel>>
    private lateinit var pagedListConfiguration: PagedList.Config
    private var lastItemId: String?=""

    fun initPagedConfig() {
        pagedListConfiguration=PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(CONTENT_PAGE_SIZE)
            .setPrefetchDistance(CONTENT_PAGE_SIZE / 2)
            .build()
    }

    fun initPagedListLiveData(factory: DataSource.Factory<Int, BaseCardModel>)=
        initPagedList(factory, DEFAULT_INITIAL_LOADED_KEY)

    private fun initPagedList(
        factory: DataSource.Factory<Int, BaseCardModel>,
        initialLoadKey: Int
    ): LiveData<PagedList<BaseCardModel>> {
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
                    if (lastItemId == null || lastItemId != itemAtEnd.getCardId())
                        if (!StringUtils.equals(lastItemId, itemAtEnd.getCardId())) {
                            rangeData(itemAtEnd.getCardId())
                            lastItemId=itemAtEnd.getCardId()
                        }
                }
            })
            .setInitialLoadKey(initialLoadKey)
            .build()
    }

    abstract fun fetchData()

    abstract fun rangeData(itemId: String)
}
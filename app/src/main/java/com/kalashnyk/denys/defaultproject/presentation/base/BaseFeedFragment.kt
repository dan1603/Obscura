package com.kalashnyk.denys.defaultproject.presentation.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedList
import com.kalashnyk.denys.defaultproject.App
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.RecyclerView
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.DiffCallbackBaseCardModel
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.PagingAdapter
import com.kalashnyk.denys.defaultproject.utils.multistate.MultiStateView

/**
 *
 */
abstract class BaseFeedFragment<V : ViewDataBinding>: BaseFragment<V>(), ItemsLoadListener<PagedList<BaseCardModel>> {


    protected var mFeedAdapter: PagingAdapter = PagingAdapter(DiffCallbackBaseCardModel())

    /**
     * @param component
     */
    abstract fun injectDependency(component: ViewModelComponent)

    abstract fun getListView(): RecyclerView

    abstract fun getRefreshView(): SwipeRefreshLayout

    abstract fun getStateView() : MultiStateView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDaggerDependencies()
    }

    private fun createDaggerDependencies() {
       activity?.apply { injectDependency((application as App).getViewModelComponent()) }
    }
}
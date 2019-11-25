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

    protected lateinit var typeFeed : String
    /**
     * @param component
     */
    abstract fun injectDependency(component: ViewModelComponent)

    /**
     *
     */
    abstract fun initListView()

    /**
     *
     */
    abstract fun getListView(): RecyclerView

    /**
     *
     */
    abstract fun getRefreshView(): SwipeRefreshLayout

    /**
     *
     */
    abstract fun getStateView() : MultiStateView

    /**
     *
     */
    abstract fun initObserver()

    /**
     *
     */
    abstract fun removeObserver()

    /**
     *
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        displayProgress()
        initListView()
        initObserver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDaggerDependencies()
    }

    override fun onDestroyView() {
        removeObserver()
        super.onDestroyView()
    }

    private fun createDaggerDependencies() {
       activity?.apply { injectDependency((application as App).getViewModelComponent()) }
    }
}
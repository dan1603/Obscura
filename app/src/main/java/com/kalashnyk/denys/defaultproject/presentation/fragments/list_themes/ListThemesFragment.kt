package com.kalashnyk.denys.defaultproject.presentation.fragments.list_themes

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kalashnyk.denys.defaultproject.BR
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.ListThemesDataBinding
import com.kalashnyk.denys.defaultproject.data.BaseModel
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.domain.FeedViewModel
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.presentation.base.BasePagingFragment
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.Pages
import com.kalashnyk.denys.defaultproject.presentation.widget.pageview.model.TabPages
import com.kalashnyk.denys.defaultproject.utils.EXTRAS_PAGES
import com.kalashnyk.denys.defaultproject.presentation.item.ItemClickListener
import com.kalashnyk.denys.defaultproject.utils.FIRST_LIST_POSITION
import com.kalashnyk.denys.defaultproject.utils.FeedLayoutManager
import com.kalashnyk.denys.defaultproject.utils.MIN_LIST_SIZE
import com.kalashnyk.denys.defaultproject.utils.extention.showSnack
import com.kalashnyk.denys.defaultproject.utils.multistate.MultiStateView
import javax.inject.Inject

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class ListThemesFragment : BasePagingFragment<ListThemesDataBinding>(), ItemClickListener<BaseModel> {

    /**
     *
     */
    var viewModel: FeedViewModel?=null
        @Inject set

    /**
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {bundle ->
            //todo check and refactor
            bundle.getSerializable(EXTRAS_PAGES)?.toString()?.let {
                screenType=it
            }
        }
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int=R.layout.fragment_list_themes

    /**
     * @return
     */
    override fun getListView(): RecyclerView=viewBinder.multiStateView.listView

    /**
     * @return
     */
    override fun getRefreshView(): SwipeRefreshLayout=viewBinder.swipeRefresh

    /**
     *
     */
    override fun getStateView(): MultiStateView=viewBinder.multiStateView.multiState

    /**
     *
     */
    override fun initListView() {
        context?.let {
            val layoutManager=
                FeedLayoutManager(it)

            getListView().layoutManager=
                layoutManager

            getListView().adapter=
                pagingAdapter


            pagingAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {

                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    super.onItemRangeInserted(positionStart, itemCount)
                    if (positionStart == FIRST_LIST_POSITION && itemCount == MIN_LIST_SIZE) {
                        getListView().scrollToPosition(FIRST_LIST_POSITION)
                    }
                }
            })

            viewModel?.getRefreshing().let { viewBinder.setVariable(BR.refreshing, it) }

            viewModel?.isLoading().let { viewBinder.setVariable(BR.loading, it) }
        }
    }

    /**
     *
     */
    override fun initObserver(screenType : String) {
        viewModel?.initLiveData(screenType, this, this)
        viewModel?.getPagedList()?.observe(this, Observer(this@ListThemesFragment::onItemsLoaded))
    }

    /**
     *
     */
    override fun removeObserver() {
        viewModel?.getPagedList()?.removeObservers(this)
    }

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: ListThemesDataBinding) {
        viewModel?.fetchData(screenType)
        binding.swipeRefresh.setOnRefreshListener {
            viewModel?.setRefreshing(true)
            viewModel?.fetchData(screenType)
        }
    }

    /**
     *
     */
    override fun onItemsLoaded(items: PagedList<BaseCardModel>?) {
        if (items.isNullOrEmpty()) {
            getStateView().viewState=MultiStateView.VIEW_STATE_EMPTY
        } else {
            pagingAdapter.submitList(items)
            getStateView().viewState=MultiStateView.VIEW_STATE_CONTENT
        }
    }

    /**
     *
     */
    override fun displayProgress() {
        getStateView().viewState=MultiStateView.VIEW_STATE_LOADING
    }

    /**
     *
     */
    override fun onLoadError(errorMessage: String) {
        getStateView().viewState=MultiStateView.VIEW_STATE_ERROR
        activity?.showSnack(errorMessage)
    }

    override fun onDetach() {
        viewModel?.clearCachedItems(screenType)
        super.onDetach()
    }

    companion object {
        /**
         *
         */
        @JvmStatic
        fun newInstance(): ListThemesFragment {
            return ListThemesFragment()
        }

        /**
         *
         */
        @JvmStatic
        fun newInstance(pages: TabPages) =
            ListThemesFragment().apply {
                arguments = Bundle().apply {
                    this.putSerializable(EXTRAS_PAGES, pages)
                }
            }

        /**
         *
         */
        @JvmStatic
        fun newInstance(pages: Pages) =
            ListThemesFragment().apply {
                arguments = Bundle().apply {
                    this.putSerializable(EXTRAS_PAGES, pages)
                }
            }
    }

    override fun onClick(item: BaseModel) {
        //TODO
    }
}

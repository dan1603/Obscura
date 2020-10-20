package com.kalashnyk.denys.defaultproject.presentation.fragments.list_users

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kalashnyk.denys.defaultproject.BR
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.ListUsersDataBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.domain.UserViewModel
import com.kalashnyk.denys.defaultproject.presentation.base.BasePagingFragment
import com.kalashnyk.denys.defaultproject.presentation.widget.pageview.model.TabPages
import com.kalashnyk.denys.moduleproject.utils.EXTRAS_PAGES
import com.kalashnyk.denys.moduleproject.utils.FIRST_LIST_POSITION
import com.kalashnyk.denys.defaultproject.utils.FeedLayoutManager
import com.kalashnyk.denys.moduleproject.utils.MIN_LIST_SIZE
import com.kalashnyk.denys.defaultproject.utils.extention.showSnack
import com.kalashnyk.denys.defaultproject.utils.multistate.MultiStateView
import com.kalashnyk.denys.moduleproject.data_source.data.BaseCardModel
import com.kalashnyk.denys.moduleproject.data_source.data.ItemClickListener
import com.kalashnyk.denys.moduleproject.data_source.database.entity.UserEntity
import javax.inject.Inject

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class ListUsersFragment : BasePagingFragment<ListUsersDataBinding>(),
    ItemClickListener<UserEntity> {


    /**
     *
     */
    var viewModel: UserViewModel?=null
        @Inject set

    /**
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //todo check and refactor
            screenType=it.getSerializable(EXTRAS_PAGES).toString()
        }
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int=R.layout.fragment_list_users

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
    override fun initObserver(screenType: String) {
        viewModel?.initLiveData(screenType, this, this)
        viewModel?.getPagedList()?.observe(this, Observer(this@ListUsersFragment::onItemsLoaded))
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
    override fun setupViewLogic(binder: ListUsersDataBinding) {
        viewModel?.fetchData(screenType)
        binder.swipeRefresh.setOnRefreshListener {
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
        @JvmStatic
        fun newInstance(): ListUsersFragment {
            return ListUsersFragment()
        }

        /**
         *
         */
        @JvmStatic
        fun newInstance(pages: TabPages)=
            ListUsersFragment().apply {
                arguments=Bundle().apply {
                    this.putSerializable(EXTRAS_PAGES, pages)
                }
            }
    }

    override fun onClick(item: UserEntity) {
        getBaseActivity().goToDetailProfileActivity(item.id)
    }
}

package com.kalashnyk.denys.defaultproject.presentation.fragments.list_messages

import android.os.Bundle
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.lifecycle.Observer
import com.kalashnyk.denys.defaultproject.BR
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.MessagesDataBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.domain.MessagesViewModel
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.presentation.base.BasePagingFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.list_themes.ListThemesFragment
import com.kalashnyk.denys.defaultproject.presentation.item.ItemClickListener
import com.kalashnyk.denys.defaultproject.presentation.widget.pageview.model.TabPages
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.MessagesEntity
import com.kalashnyk.denys.defaultproject.utils.EXTRAS_PAGES
import com.kalashnyk.denys.defaultproject.utils.FIRST_LIST_POSITION
import com.kalashnyk.denys.defaultproject.utils.FeedLayoutManager
import com.kalashnyk.denys.defaultproject.utils.MIN_LIST_SIZE
import com.kalashnyk.denys.defaultproject.utils.extention.showSnack
import com.kalashnyk.denys.defaultproject.utils.multistate.MultiStateView
import javax.inject.Inject

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class ListMessagesFragment : BasePagingFragment<MessagesDataBinding>(),
    ItemClickListener<MessagesEntity> {

    /**
     *
     */
    var viewModel: MessagesViewModel?=null
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

    /**
     * @return
     */
    override fun getLayoutId(): Int = R.layout.fragment_messages
//
//    /**
//     * @param binding
//     */
//    override fun setupViewLogic(binding: MessagesDataBinding) {
//        binding.tvMessages.setText("Messages")
//    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

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
        viewModel?.getPagedList()?.observe(this, Observer(this@ListMessagesFragment::onItemsLoaded))
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
    override fun setupViewLogic(binder: MessagesDataBinding) {
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
        fun newInstance(): ListMessagesFragment {
            return ListMessagesFragment()
        }

        /**
         *
         */
        @JvmStatic
        fun newInstance(pages: TabPages) =
            ListMessagesFragment().apply {
                arguments = Bundle().apply {
                    this.putSerializable(EXTRAS_PAGES, pages)
                }
            }
    }

    override fun onClick(item: MessagesEntity) {
        getBaseActivity().goToConversation(item.talkerId)
    }
}

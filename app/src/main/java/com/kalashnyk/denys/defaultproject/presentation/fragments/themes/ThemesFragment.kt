package com.kalashnyk.denys.defaultproject.presentation.fragments.themes

import android.os.Bundle
import androidx.databinding.ObservableBoolean
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.ThemesDataBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.domain.FeedViewModel
import com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist.BaseCardModel
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFeedFragment
import com.kalashnyk.denys.defaultproject.utils.extention.showSnack
import com.kalashnyk.denys.defaultproject.utils.multistate.MultiStateView
import javax.inject.Inject

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class ThemesFragment : BaseFeedFragment<ThemesDataBinding>() {


    /**
     *
     */
    var viewModel: FeedViewModel? = null
        @Inject set

    /**
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //ToDo get extras from bundle
        }
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int=R.layout.fragment_themes

    /**
     * @return
     */
    override fun getListView(): RecyclerView = viewBinder.multiStateView.listView

    /**
     * @return
     */
    override fun getRefreshView(): SwipeRefreshLayout = viewBinder.swipeRefresh

    override fun getStateView(): MultiStateView = viewBinder.multiStateView.multiState

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: ThemesDataBinding) {
        binding.refreshing=ObservableBoolean(false)
        binding.loading=ObservableBoolean(true)
    }

    override fun onItemsLoaded(items: PagedList<BaseCardModel>) {
        viewBinder.loading = ObservableBoolean(false)
        if (items.isNullOrEmpty()) {
            getStateView().viewState = MultiStateView.VIEW_STATE_EMPTY
        } else {
            mFeedAdapter.submitList(items)
            getStateView().viewState = MultiStateView.VIEW_STATE_CONTENT
        }
    }

    override fun displayProgress() {
        //todo for test
        viewBinder.loading = ObservableBoolean(true)
    }

    override fun onLoadError(errorMessage: String) {
        //todo for test
        getStateView().viewState = MultiStateView.VIEW_STATE_ERROR
        activity?.showSnack(errorMessage)
    }


    companion object {
        @JvmStatic
        fun newInstance(): ThemesFragment {
            return ThemesFragment()
        }
    }
}

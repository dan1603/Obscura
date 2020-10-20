package com.kalashnyk.denys.defaultproject.presentation.fragments.main_tabs.tabs_people

import android.os.Bundle
import com.kalashnyk.denys.defaultproject.databinding.TabsPeopleDataBinding
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment
import com.kalashnyk.denys.defaultproject.presentation.widget.pageview.model.TabPages

/**
 *
 */
class TabsPeopleFragment : BaseFragment<TabsPeopleDataBinding>() {
    val bindingModel =
        TabsPeopleBindingModel(
            initPeopleTabs()
        )

    /**
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //ToDo get extras from bundle
        }
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int = R.layout.fragment_tabs_people

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: TabsPeopleDataBinding) {
        binding.bindingModel = bindingModel
    }

    private fun initPeopleTabs() : List<TabPages> {
        val tabs: MutableList<TabPages> = mutableListOf()
        tabs.add(TabPages.TAB_FOLLOWING)
        tabs.add(TabPages.TAB_PEOPLE)
        return tabs
    }

    companion object {
        @JvmStatic
        fun newInstance(): TabsPeopleFragment {
            return TabsPeopleFragment()
        }
    }
}

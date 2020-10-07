package com.kalashnyk.denys.defaultproject.presentation.navigation

import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.presentation.fragments.main_tabs.tabs_people.TabsPeopleFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.main_tabs.tabs_themes.TabsThemesFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.list_messages.ListMessagesFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.list_themes.ListThemesFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.list_users.ListUsersFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.profile.ProfileFragment
import com.kalashnyk.denys.defaultproject.presentation.widget.pageview.model.TabPages

class BottomNavigationHandler(
    private val bottomNav: AHBottomNavigation,
    private val viewPager: ViewPager,
    private val actionBar: ActionBar?,
    private val manager: FragmentManager
) {
    init {
        setupBottomNav()
        setupViewPager()
        setupTitle(0)
    }

    private fun setupBottomNav()=bottomNav?.apply {
        getPages().forEach { addItem(it.second) }
        accentColor = ContextCompat.getColor(this.context, R.color.colorWhite)
        inactiveColor = ContextCompat.getColor(this.context, R.color.bottom_inactive)
        defaultBackgroundColor = ContextCompat.getColor(this.context, R.color.soft_blue)
        titleState = AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE
        setUseElevation(true)
        setOnTabSelectedListener { position, _ ->
            viewPager?.currentItem=position
            setupTitle(position)
            return@setOnTabSelectedListener true
        }
        setCurrentItem(0, true)
    }

    private fun setupTitle(index: Int) = actionBar?.apply {
        title=getPages()[index].second.getTitle(actionBar.themedContext)
    }

    private fun setupViewPager() = viewPager?.apply {
        this.adapter = BottomNavigationAdapter(manager).apply {
            getPages().forEach { add(it.first) }
        }
        this.offscreenPageLimit = getPages().size
        this.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) { }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

            override fun onPageSelected(position: Int) {
                bottomNav?.setCurrentItem(position, false)
                setupTitle(position)
            }
        })
    }

    private fun getPages()=arrayListOf<Pair<Fragment, AHBottomNavigationItem>>(
        Pair(
            ListThemesFragment.newInstance(TabPages.TAB_FEED),
            AHBottomNavigationItem(
                bottomNav?.context?.getString(R.string.bottom_item_themes),
                R.drawable.ic_themes_active
            )
        ),
        Pair(
            ProfileFragment.newInstance(),
            AHBottomNavigationItem(
                bottomNav?.context?.getString(R.string.bottom_item_profile),
                R.drawable.ic_profile_active
            )
        ),
        Pair(
            ListUsersFragment.newInstance(TabPages.TAB_PEOPLE),
            AHBottomNavigationItem(
                bottomNav?.context?.getString(R.string.bottom_item_people),
                R.drawable.ic_people_active
            )
        ),
        Pair(
            ListMessagesFragment.newInstance(TabPages.TAB_MESSAGES),
            AHBottomNavigationItem(
                bottomNav?.context?.getString(R.string.bottom_item_messages),
                R.drawable.ic_chats_active
            )
        )
    )
}
package com.kalashnyk.denys.defaultproject.presentation.activities.main

import androidx.core.content.ContextCompat
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.MainDataBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.domain.AllUsersViewModel
import com.kalashnyk.denys.defaultproject.presentation.adapter.UserAdapter
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity
import com.kalashnyk.denys.defaultproject.presentation.fragments.messages.MessagesFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.people.PeopleFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.profile.ProfileFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.themes.ThemesFragment
import com.kalashnyk.denys.defaultproject.presentation.item.IUserItemClickListener
import com.kalashnyk.denys.defaultproject.usecases.repository.database.entity.UserEntity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class MainActivity : BaseActivity<MainDataBinding>() {

    var viewModel: AllUsersViewModel? = null
        @Inject set

    private lateinit var ahItem1: AHBottomNavigationItem
    private lateinit var ahItem2: AHBottomNavigationItem
    private lateinit var ahItem3: AHBottomNavigationItem
    private lateinit var ahItem4: AHBottomNavigationItem

    private var onTabSelectedListener = AHBottomNavigation.OnTabSelectedListener { position: Int, wasSelected: Boolean ->
        if (wasSelected) return@OnTabSelectedListener false

        when (position) {
            0 -> { replaceFragment(R.id.mainFragmentContainer, ThemesFragment.newInstance(), false, false) }
            1 -> { replaceFragment(R.id.mainFragmentContainer, ProfileFragment.newInstance(), false, false) }
            2 -> { replaceFragment(R.id.mainFragmentContainer, PeopleFragment.newInstance(), false, false) }
            3 -> { replaceFragment(R.id.mainFragmentContainer, MessagesFragment.newInstance(), false, false) }
        }

        return@OnTabSelectedListener true
    }

    /**
     * @param component
     */
    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }
    /**
     * @return
     */
    override fun getLayoutId(): Int = R.layout.activity_main

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: MainDataBinding) {
        initBottomBar()
        replaceFragment(R.id.mainFragmentContainer, ThemesFragment.newInstance(), false, false)
    }

    private fun initBottomBar() {
        bottomNavigation.setOnTabSelectedListener(onTabSelectedListener)

        bottomNavigation.accentColor = ContextCompat.getColor(this, R.color.colorWhite)
        bottomNavigation.inactiveColor = ContextCompat.getColor(this, R.color.bottom_inactive)
        bottomNavigation.defaultBackgroundColor = ContextCompat.getColor(this, R.color.soft_blue)
        bottomNavigation.titleState = AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE
        bottomNavigation.setUseElevation(true)

        ahItem1 = AHBottomNavigationItem(getString(R.string.bottom_item_themes), R.drawable.ic_themes_active)
        ahItem2 = AHBottomNavigationItem(getString(R.string.bottom_item_profile), R.drawable.ic_profile_active)
        ahItem3 = AHBottomNavigationItem(getString(R.string.bottom_item_people), R.drawable.ic_people_active)
        ahItem4 = AHBottomNavigationItem(getString(R.string.bottom_item_messages), R.drawable.ic_chats_active)

        bottomNavigation.addItem(ahItem1)
        bottomNavigation.addItem(ahItem2)
        bottomNavigation.addItem(ahItem3)
        bottomNavigation.addItem(ahItem4)
    }

    private fun initRecyclerView(users: List<UserEntity>) {
        //val manager = LinearLayoutManager(this)
        val userAdapter = UserAdapter(this, users, itemClickListener)
        userAdapter.setItemClickListener(itemClickListener)
        //rvUsers.layoutManager = manager
        //rvUsers.adapter = userAdapter
    }

    private val itemClickListener = object : IUserItemClickListener<UserEntity> {
        override fun openDetail(entity: UserEntity) {
            openItemDetail(entity.id)
        }
    }

    private fun openItemDetail(id: Int) {
        navigator.openDetailScreen(id)
    }
}

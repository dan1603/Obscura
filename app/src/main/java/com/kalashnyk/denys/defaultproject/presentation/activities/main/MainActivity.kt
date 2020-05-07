package com.kalashnyk.denys.defaultproject.presentation.activities.main

import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.MainDataBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.domain.AllUsersViewModel
import com.kalashnyk.denys.defaultproject.presentation.adapter.UserAdapter
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity
import com.kalashnyk.denys.defaultproject.presentation.fragments.main_tabs.tabs_themes.TabsThemesFragment
import com.kalashnyk.denys.defaultproject.presentation.item.IUserItemClickListener
import com.kalashnyk.denys.defaultproject.presentation.navigation.BottomNavigationHandler
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.UserEntity
import com.kalashnyk.denys.defaultproject.utils.extention.replaceFragment
import javax.inject.Inject

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class MainActivity : BaseActivity<MainDataBinding>() {

    var viewModel: AllUsersViewModel? = null
        @Inject set

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
     * @param binder
     */
    override fun setupViewLogic(binder: MainDataBinding) {
        BottomNavigationHandler(
            binder.bottomNavigation,
            binder.mainFragmentContainer,
            supportActionBar,
            supportFragmentManager
        )
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

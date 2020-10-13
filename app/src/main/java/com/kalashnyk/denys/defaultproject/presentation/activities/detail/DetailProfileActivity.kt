package com.kalashnyk.denys.defaultproject.presentation.activities.detail

import android.view.MenuItem
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.DetailProfileDataBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.domain.SingleProfileViewModel
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity
import com.kalashnyk.denys.defaultproject.presentation.fragments.list_themes.ListThemesFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.profile.ProfileFragment
import com.kalashnyk.denys.defaultproject.utils.DETAIL_ID
import javax.inject.Inject

/**
 *
 */
class DetailProfileActivity: BaseActivity<DetailProfileDataBinding>() {

    override fun injectDependency(component: ViewModelComponent) { }

    override fun getLayoutId(): Int = R.layout.activity_detail_profile

    override fun setupViewLogic(binder: DetailProfileDataBinding) {
        val id = intent.getIntExtra(DETAIL_ID, -1)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Profile"
        supportFragmentManager.beginTransaction()
            .add(R.id.profileFragmentContainer, ProfileFragment.newInstance(id))
            .commit()
    }

    /**
     *
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}
package com.kalashnyk.denys.defaultproject.presentation.activities.detail

import android.view.MenuItem
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.DetailFeedDataBinding
import com.kalashnyk.denys.defaultproject.databinding.DetailProfileDataBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity

class DetailFeedActivity: BaseActivity<DetailFeedDataBinding>() {
    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_detail_feed

    override fun setupViewLogic(binder: DetailFeedDataBinding) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Feed element"
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
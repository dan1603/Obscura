package com.kalashnyk.denys.defaultproject.presentation.activities.detail

import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.DetailDataBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity
import com.kalashnyk.denys.defaultproject.utils.DETAIL_ID
import kotlinx.android.synthetic.main.activity_detail.*

class ConversationActivity: BaseActivity<DetailDataBinding>() {

    private var userId: Int = 0

    override fun injectDependency(component: ViewModelComponent) {

    }

    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun setupViewLogic(binder: DetailDataBinding) {
        userId = intent.getIntExtra(DETAIL_ID, 0)
    }

    override fun onResume() {
        super.onResume()
        txtDetailSurname.text = userId.toString()
    }
}
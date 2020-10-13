package com.kalashnyk.denys.defaultproject.presentation.activities.detail

import android.view.MenuItem
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.ConversationDataBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.domain.ConversationViewModel
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity
import com.kalashnyk.denys.defaultproject.utils.DETAIL_ID
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

/**
 *
 */
class ConversationActivity: BaseActivity<ConversationDataBinding>() {

    var viewModel: ConversationViewModel? = null
        @Inject set

    private var userId: Int = 0

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_conversation

    override fun setupViewLogic(binder: ConversationDataBinding) {
        userId = intent.getIntExtra(DETAIL_ID, 0)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title=getString(R.string.conversation_title)
        }
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

    override fun onResume() {
        super.onResume()
//        txtDetailSurname.text = userId.toString()
    }
}
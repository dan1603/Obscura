package com.kalashnyk.denys.defaultproject.presentation.activities.detail

import android.view.MenuItem
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.DetailDataBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.domain.SingleUserViewModel
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.UserEntity
import java.util.*
import javax.inject.Inject

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class DetailActivity : BaseActivity<DetailDataBinding>() {


    var viewModel: SingleUserViewModel? = null
        @Inject set

    private var userId: Int = 0

    /**
     * @param component
     */
    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int = R.layout.activity_detail

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: DetailDataBinding) {
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        initViewModel()
    }

    /**
     *  @param item
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViewModel() {
        userId = intent.getIntExtra(getString(R.string.EXTRAS_ID), 0)
        viewModel?.getItem(userId)
//        viewModel?.getLiveDataItem()?.observe(this, Observer { it?.let { initTextViews(it) } })
    }

    private fun initTextViews(user: UserEntity) {
        viewBinding.txtDetailId.text = user.id.toString()
        viewBinding.txtDetailName.text = user.name
        viewBinding.txtDetailSurname.text = user.surname
        viewBinding.txtDetailFathername.text = user.fathername
        initActionBar(user.name)
    }

    private fun initActionBar(title: String) {
        Objects.requireNonNull(supportActionBar)?.title = title
    }
}

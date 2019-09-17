package com.kalashnyk.denys.defaultproject.presentation.base

import android.app.ActionBar
import android.os.Bundle
import androidx.annotation.DrawableRes

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kalashnyk.denys.defaultproject.domain.BaseViewModel
import com.kalashnyk.denys.defaultproject.utils.AppLog
import com.kalashnyk.denys.defaultproject.utils.extention.hideKeyboard
import com.kalashnyk.denys.defaultproject.utils.extention.showSnack
import com.kalashnyk.denys.defaultproject.utils.extention.showToast

abstract class BaseFragment<V : ViewDataBinding> : Fragment() {

    protected lateinit var viewBinder: V

    private val appBar: ActionBar? = activity?.actionBar

    companion object {
        private const val DEBUG_ENABLED = false

    }

    protected fun disableHomeAsUp() = appBar?.setDisplayHomeAsUpEnabled(false)

    protected fun initializeNavigationBar(title: String, showBackButton: Boolean, @DrawableRes resId: Int) {
        appBar?.apply {
            this.setDisplayHomeAsUpEnabled(showBackButton)
            this.setHomeAsUpIndicator(resId)
            this.elevation = 4f
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> fragmentManager?.popBackStackImmediate()
        }
        return super.onOptionsItemSelected(item)
    }

    abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinder = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        setupViewLogic(this.viewBinder)
        return viewBinder.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel()?.macroLoadingState?.observe(this,
            Observer { it?.let { onLoadingStateChanged(it) } })
    }

    /**
     *  Fragments should return their viewmodel from this method if they have one
     */
    protected open fun getViewModel() : BaseViewModel? {
        return null
    }

    /**
     * Fragments with a macro loading state should override this method to change the UI
     * depending on the loading state
     */
    protected open fun onLoadingStateChanged(loadingState : LoadingState) {}
//todo  move to extension
    protected open fun log(message : String) {
        @Suppress("ConstantConditionIf")
        if (DEBUG_ENABLED) {
            AppLog.d("* $message")
        }
    }

    open fun reset() {}

    abstract fun setupViewLogic(binder : V)

    /**
     *
     */
    protected fun showToast(text: String) = activity?.showToast(text)
    /**
     *
     */
    protected fun showSnack(text: String) = activity?.showSnack(text)
    /**
     *
     */
    protected fun hideKeyboard() = activity?.hideKeyboard()
}
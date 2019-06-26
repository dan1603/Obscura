package com.kalashnyk.denys.defaultproject.presentation.base

import android.app.ActionBar
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.kalashnyk.denys.defaultproject.utils.hideKeyboardEx
import com.kalashnyk.denys.defaultproject.utils.showSnack
import com.kalashnyk.denys.defaultproject.utils.showToast

abstract class BaseFragment : Fragment() {
    private val appBar: ActionBar? = activity?.actionBar
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

    abstract fun getLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayout(), container, false)
        return view
    }

    protected fun showToast(text: String) = activity?.showToast(text)
    protected fun showSnack(text: String) = activity?.showSnack(text)
    protected fun hideKeyboard() = activity?.hideKeyboardEx()
}
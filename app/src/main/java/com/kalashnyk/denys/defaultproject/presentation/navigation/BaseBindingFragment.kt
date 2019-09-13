package com.kalashnyk.denys.defaultproject.presentation.navigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kalashnyk.denys.defaultproject.domain.BaseViewModel
import com.kalashnyk.denys.defaultproject.presentation.base.LoadingState
import com.kalashnyk.denys.defaultproject.utils.AppLog
//todo watch and remove
abstract class BaseBindingFragment<B : ViewDataBinding> : Fragment() {
    protected lateinit var binding : B
    protected abstract val layoutId : Int

    companion object {
        private const val DEBUG_ENABLED = false

    }

    fun getNavigatorIfAvailable() : FragmentNavigator {

        var navigator : FragmentNavigator? = null
        if (activity != null && !activity!!.isFinishing) {
            when {
                this is FragmentNavigator -> {
                    log("Returning THIS Navigator")
                    navigator = this
                }
                parentFragment == null -> if (activity is FragmentNavigator) {
                    //   AppLog.d("Returning ACTIVITY Navigator")
                    navigator = activity as FragmentNavigator
                }
                parentFragment is FragmentNavigator -> {
                    log(
                        "Returning PARENT FRAGMENT Navigator of hash " +
                            "${parentFragment?.hashCode()}/${parentFragment!!.childFragmentManager}"
                    )
                    navigator = (parentFragment as FragmentNavigator)

                }
                parentFragment?.parentFragment is FragmentNavigator -> {
                    log(
                        "Returning PARENT FRAGMENT Navigator of hash " +
                            "${parentFragment?.hashCode()}/${parentFragment!!.childFragmentManager}"
                    )
                    navigator = (parentFragment!!.parentFragment as FragmentNavigator)

                }
            }
        }
        if (navigator == null) {
            log("Returning FALLBACK Navigator")
            navigator = FragmentNavigatorImpl(childFragmentManager)
        }
        return navigator
    }

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
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

    //Return true if this ia a top level screen, where user should be alerted if no network
    protected open fun shouldShowNoConnectionDialog() : Boolean {
        return false
    }
    //todo move to extensions
    internal fun hideKeyboard() {
        activity?.let {
            val view = it.currentFocus
            if (view != null) {
                val imm =
                    it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                @Suppress("UNNECESSARY_SAFE_CALL")
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }
    //todo move to extensions
    internal fun showKeyboard(view : View) {
        activity?.let {
            val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
    //todo move to extensions
    protected open fun log(message : String) {
        @Suppress("ConstantConditionIf")
        if (DEBUG_ENABLED) {
            AppLog.d("* $message")
        }
    }

    open fun reset() {}
}
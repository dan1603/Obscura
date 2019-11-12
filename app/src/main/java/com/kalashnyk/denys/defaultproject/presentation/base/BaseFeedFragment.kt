package com.kalashnyk.denys.defaultproject.presentation.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.kalashnyk.denys.defaultproject.App
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent

/**
 *
 */
abstract class BaseFeedFragment<V : ViewDataBinding>: BaseFragment<V>() {

    /**
     * @param component
     */
    abstract fun injectDependency(component: ViewModelComponent)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDaggerDependencies()
    }

    private fun createDaggerDependencies() {
       activity?.apply { injectDependency((application as App).getViewModelComponent()) }
    }
}
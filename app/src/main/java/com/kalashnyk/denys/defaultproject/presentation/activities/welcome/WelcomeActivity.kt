package com.kalashnyk.denys.defaultproject.presentation.activities.welcome

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.WelcomeDataBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity

/**
 *
 */
class WelcomeActivity :  BaseActivity() {

    private lateinit var binding: WelcomeDataBinding

    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent {
            return Intent(context, WelcomeActivity::class.java).apply {
                this.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
    }

    override fun injectDependency(component: ViewModelComponent) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        binding.navigator = navigator
    }
}

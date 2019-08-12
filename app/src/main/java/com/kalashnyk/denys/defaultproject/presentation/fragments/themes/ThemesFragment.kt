package com.kalashnyk.denys.defaultproject.presentation.fragments.themes

import android.os.Bundle
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.ThemesDataBinding
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class ThemesFragment : BaseFragment<ThemesDataBinding>() {

    /**
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //ToDo get extras from bundle
        }
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int = R.layout.fragment_themes

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: ThemesDataBinding) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        @JvmStatic
        fun newInstance(): ThemesFragment {
            return ThemesFragment()
        }
    }
}

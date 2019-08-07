package com.kalashnyk.denys.defaultproject.presentation.fragments.themes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment


class ThemesFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //ToDo get extras from bundle
        }
    }

    override fun getLayout(): Int  = R.layout.fragment_themes

    override fun setupViewLogic(view: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        @JvmStatic
        fun newInstance(): ThemesFragment {
            return ThemesFragment()
        }
    }
}

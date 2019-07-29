package com.kalashnyk.denys.defaultproject.presentation.fragments.themes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment


class ThemesFragment : BaseFragment() {

    override fun getLayout(): Int {
        return 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_themes, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(): ThemesFragment {
            return ThemesFragment()
        }
    }
}

package com.kalashnyk.denys.defaultproject.presentation.fragments.people

import android.os.Bundle
import android.view.View
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment

class PeopleFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //ToDo get extras from bundle
        }
    }

    override fun getLayout(): Int  = R.layout.fragment_people

    override fun setupViewLogic(view: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        @JvmStatic
        fun newInstance(): PeopleFragment {
            return PeopleFragment()
        }
    }
}

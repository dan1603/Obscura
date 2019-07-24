package com.kalashnyk.denys.defaultproject.presentation.fragments.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment

class PeopleFragment : BaseFragment() {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_people, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(): PeopleFragment {
            val fragment: PeopleFragment = PeopleFragment()
            return fragment
        }
    }
}

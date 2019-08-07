package com.kalashnyk.denys.defaultproject.presentation.fragments.profile

import android.os.Bundle
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.ProfileDataBinding
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment

class ProfileFragment : BaseFragment<ProfileDataBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //ToDo get extras from bundle
        }
    }

    override fun getLayoutId(): Int  = R.layout.fragment_profile

    override fun setupViewLogic(binding: ProfileDataBinding) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        @JvmStatic
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}

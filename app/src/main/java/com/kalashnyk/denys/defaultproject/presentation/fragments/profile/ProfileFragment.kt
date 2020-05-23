package com.kalashnyk.denys.defaultproject.presentation.fragments.profile

import android.os.Bundle
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.ProfileDataBinding
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class ProfileFragment : BaseFragment<ProfileDataBinding>() {

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
    override fun getLayoutId(): Int = R.layout.fragment_profile

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: ProfileDataBinding) {
      //binding.tvProfile.setText("Profile")
    }

    companion object {
        @JvmStatic
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}

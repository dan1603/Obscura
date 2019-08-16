package com.kalashnyk.denys.defaultproject.presentation.fragments.people

import android.os.Bundle
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.PeopleDataBinding
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class PeopleFragment : BaseFragment<PeopleDataBinding>() {

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
    override fun getLayoutId(): Int = R.layout.fragment_people

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: PeopleDataBinding) {
        binding.tvPeople.setText("People")
    }

    companion object {
        @JvmStatic
        fun newInstance(): PeopleFragment {
            return PeopleFragment()
        }
    }
}

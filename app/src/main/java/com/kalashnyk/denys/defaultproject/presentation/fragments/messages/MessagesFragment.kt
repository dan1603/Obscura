package com.kalashnyk.denys.defaultproject.presentation.fragments.messages

import android.os.Bundle
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.MessagesDataBinding
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment

class MessagesFragment : BaseFragment<MessagesDataBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //ToDo get extras from bundle
        }
    }

    override fun getLayoutId(): Int  = R.layout.fragment_messages

    override fun setupViewLogic(binding: MessagesDataBinding) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        @JvmStatic
        fun newInstance(): MessagesFragment {
            return MessagesFragment()
        }
    }
}

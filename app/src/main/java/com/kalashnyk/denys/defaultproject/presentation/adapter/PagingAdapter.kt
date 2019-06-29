package com.kalashnyk.denys.defaultproject.presentation.adapter

import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil

class PagingAdapter(diffUtil: DiffUtil.ItemCallback<BaseCardModel>) :
    MultiTypeDataBoundAdapter<BaseCardModel, ViewDataBinding>(diffUtil) {

    override fun getItemLayoutId(position: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
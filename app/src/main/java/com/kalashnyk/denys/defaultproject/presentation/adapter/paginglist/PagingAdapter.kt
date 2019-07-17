package com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist

import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
//todo finished paging adapter
class PagingAdapter(diffUtil: DiffUtil.ItemCallback<BaseCardModel>) :
    MultiTypeDataBoundAdapter<BaseCardModel, ViewDataBinding>(diffUtil) {

    override fun getItemLayoutId(position: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
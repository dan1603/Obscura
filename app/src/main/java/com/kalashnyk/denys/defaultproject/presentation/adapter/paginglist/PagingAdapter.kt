package com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
//todo finished paging adapter
class PagingAdapter(diffUtil: DiffUtil.ItemCallback<BaseCardModel>) :
    MultiTypeDataBoundAdapter<BaseCardModel, ViewDataBinding>(diffUtil) {

    override fun getItemLayoutId(position: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
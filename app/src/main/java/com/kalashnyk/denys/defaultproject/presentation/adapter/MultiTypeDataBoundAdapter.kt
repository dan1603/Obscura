package com.kalashnyk.denys.defaultproject.presentation.adapter

import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import com.android.databinding.library.baseAdapters.BR

abstract class MultiTypeDataBoundAdapter<T, V : ViewDataBinding> (diffUtil: DiffUtil.ItemCallback<T>) : BaseDataBoundPagingAdapter<T, V>(diffUtil) {

    fun isEmpty(): Boolean {
        return getItemCount() == 0
    }

    override fun bindItem(holder: DataBoundViewHolder<V>, position: Int, payloads: List<Any>) {
        if (payloads.isEmpty() || payloads.contains(DiffCallbackBaseCardModel.CONTENT)) {
            holder.binding.setVariable(BR.data, getItem(position))
        }
    }

    override fun getItemCount(): Int {
        return if (getCurrentList() != null) getCurrentList()!!.size else 0
    }
}

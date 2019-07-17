package com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class DataBoundViewHolder<T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun <T : ViewDataBinding> create(parent : ViewGroup, @LayoutRes layoutId: Int) : DataBoundViewHolder<T> {
            val binding = DataBindingUtil.inflate<T>(LayoutInflater.from(parent.context), layoutId, parent, false)
            return DataBoundViewHolder(binding)
        }
    }
}
package com.kalashnyk.denys.defaultproject.presentation.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<VH : RecyclerView.ViewHolder, M, L>
    (protected var list: MutableList<M>, protected var clickListener: L) : RecyclerView.Adapter<VH>() {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.tag = list[position]
    }

    fun getItem(position: Int): M {
        return list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun add(item: M) {
        list.add(item)
    }

    fun addAll(list: List<M>) {
        for (item in list) {
            add(item)
        }
    }

    fun remove(item: M) {
        val position = list.indexOf(item)
        if (position > -1) {
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: L) {
        clickListener = listener
    }
}

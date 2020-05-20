package com.kalashnyk.denys.defaultproject.presentation.item

import androidx.recyclerview.widget.RecyclerView
import com.kalashnyk.denys.defaultproject.databinding.ItemLocationBinding
import com.kalashnyk.denys.defaultproject.presentation.activities.location.LocationModel


class LocationViewHolder(val binding: ItemLocationBinding): RecyclerView.ViewHolder(binding.root) {

    private var location: LocationModel? = null

    fun bind(location: LocationModel) {
        this.location = location
        binding.location = this.location
    }
}
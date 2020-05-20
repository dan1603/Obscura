package com.kalashnyk.denys.defaultproject.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kalashnyk.denys.defaultproject.databinding.ItemLocationBinding
import com.kalashnyk.denys.defaultproject.presentation.activities.location.LocationChooserPlaceCallback
import com.kalashnyk.denys.defaultproject.presentation.activities.location.LocationModel
import com.kalashnyk.denys.defaultproject.presentation.base.BaseAdapter
import com.kalashnyk.denys.defaultproject.presentation.item.LocationViewHolder

class LocationAdapter(
    private val context: Context,
    private var locations: ArrayList<LocationModel>,
    private val callback: LocationChooserPlaceCallback
) : BaseAdapter<LocationViewHolder, LocationModel>(locations) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder =
        LocationViewHolder(ItemLocationBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(locations[position])
        holder.binding.root.setOnClickListener { handleItemClick(position) }
    }

    override fun getItemCount(): Int = locations.size

    private fun handleItemClick(position: Int) {
        callback.onAddressClicked(locations[position].address)
    }
}
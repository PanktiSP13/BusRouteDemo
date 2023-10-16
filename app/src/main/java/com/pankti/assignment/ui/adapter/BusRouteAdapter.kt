package com.pankti.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pankti.assignment.R
import com.pankti.assignment.databinding.LayoutListItemBusRouteBinding
import com.pankti.assignment.domain.BusDataUIModel

class BusRouteAdapter(var busRouteList: List<BusDataUIModel> = mutableListOf()) : RecyclerView.Adapter<BusRouteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<LayoutListItemBusRouteBinding>(LayoutInflater.from(parent.context), R.layout.layout_list_item_bus_route, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = busRouteList.size

    fun updateData(list: List<BusDataUIModel>) {
        busRouteList = list.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: LayoutListItemBusRouteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {

            binding.tvBusName.text = busRouteList[adapterPosition].name
            binding.tvStartDestination.text = busRouteList[adapterPosition].source
            binding.tvEndDestination.text = busRouteList[adapterPosition].destination
            binding.tvTravelTime.text = busRouteList[adapterPosition].tripDuration

            // bus track data
            if (busRouteList[adapterPosition].busTrack.isNotEmpty()){
                binding.rvBusTrackList.visibility = View.VISIBLE
                val adapter = BusTrackAdapter(busRouteList[adapterPosition].busTrack)
                binding.rvBusTrackList.adapter = adapter
            }else{
                binding.rvBusTrackList.visibility = View.GONE
            }
        }
    }
}
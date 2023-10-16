package com.pankti.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pankti.assignment.R
import com.pankti.assignment.databinding.LayoutListItemBusTrackBinding
import com.pankti.assignment.domain.RouteTiming
import com.pankti.assignment.ui.Utils.isBusAvailable
import com.pankti.assignment.ui.Utils.toFormat

class BusTrackAdapter(var busTrack: List<RouteTiming>) : RecyclerView.Adapter<BusTrackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<LayoutListItemBusTrackBinding>(LayoutInflater.from(parent.context), R.layout.layout_list_item_bus_track, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = busTrack.size

    inner class ViewHolder(var binding: LayoutListItemBusTrackBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {

            if (adapterPosition == 0) {
                binding.tvTravelTimeTitle.text = binding.root.context.getString(R.string.start)
                binding.ivImg.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.ic_walking_person))

            } else {
                if (adapterPosition == (busTrack.size - 1)) {
                    val text = busTrack[adapterPosition].tripStartTime + binding.root.context.getString(R.string.finish)
                    binding.tvTravelTimeTitle.text = text
                    binding.ivNext.visibility = View.GONE

                } else binding.tvTravelTimeTitle.text = busTrack[adapterPosition].tripStartTime

                binding.ivImg.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.ic_bus))

                val seats = busTrack[adapterPosition].totalSeats
                if (seats > 0) {
                    binding.tvSeatAvailable.text = seats.toString()
                   if (adapterPosition != (busTrack.size-1))  binding.tvSeatAvailable.visibility = View.VISIBLE
                } else binding.tvSeatAvailable.visibility = View.GONE

            }
        }
    }
}
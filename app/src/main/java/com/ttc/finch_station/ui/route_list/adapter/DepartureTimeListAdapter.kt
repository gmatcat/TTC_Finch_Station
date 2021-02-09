package com.ttc.finch_station.ui.route_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ttc.finch_station.databinding.ItemDepartureTimeBinding

class DepartureTimeListAdapter (private var departureTimes: List<String>) :
    RecyclerView.Adapter<DepartureTimeListAdapter.DepartureTimeViewHolder>() {

    inner class DepartureTimeViewHolder(val binding: ItemDepartureTimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            with(binding) {
                tvDepartureTime.text = item
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartureTimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDepartureTimeBinding.inflate(layoutInflater, parent, false)
        return DepartureTimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DepartureTimeViewHolder, position: Int) =
        holder.bind(departureTimes.get(position))

    override fun getItemCount(): Int {
        return departureTimes.size
    }

}
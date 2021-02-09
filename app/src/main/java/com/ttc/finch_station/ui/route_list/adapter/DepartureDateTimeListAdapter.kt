package com.ttc.finch_station.ui.route_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ttc.finch_station.data.model.business.DepartureDateTime
import com.ttc.finch_station.databinding.ItemDepartureDateTimeBinding

class DepartureDateTimeListAdapter(private var departureDateTimes: List<DepartureDateTime>) :
    RecyclerView.Adapter<DepartureDateTimeListAdapter.DepartureDateTimeViewHolder>() {

    lateinit var departureTimeListAdapter: DepartureTimeListAdapter

    inner class DepartureDateTimeViewHolder(val binding: ItemDepartureDateTimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DepartureDateTime) {
            with(binding) {
                tvDepartureDate.text = item.departureDate

                departureTimeListAdapter =
                    DepartureTimeListAdapter(
                        item.departureTimes
                    )
                rvDepartureTimes.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = departureTimeListAdapter
                }

            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartureDateTimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDepartureDateTimeBinding.inflate(layoutInflater, parent, false)
        return DepartureDateTimeViewHolder(binding)
    }

    override fun onBindViewHolder(holderDate: DepartureDateTimeViewHolder, position: Int) =
        holderDate.bind(departureDateTimes.get(position))

    override fun getItemCount(): Int {
        return departureDateTimes.size
    }

}
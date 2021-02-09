package com.ttc.finch_station.ui.route_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ttc.finch_station.data.model.business.StopRoute
import com.ttc.finch_station.databinding.ItemStopRouteBinding

class StopRouteListAdapter(private var stopRoutes: List<StopRoute>) :
    RecyclerView.Adapter<StopRouteListAdapter.StopTimeViewHolder>() {

    lateinit var departureDateTimeListAdapter: DepartureDateTimeListAdapter

    inner class StopTimeViewHolder(val binding: ItemStopRouteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StopRoute) {
            with(binding) {
                tvRouteName.text = item.stopRouteName

                departureDateTimeListAdapter =
                    DepartureDateTimeListAdapter(
                        item.departureTimes
                    )
                rvDepartureTimes.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = departureDateTimeListAdapter
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopTimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemStopRouteBinding.inflate(layoutInflater, parent, false)

        return StopTimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StopTimeViewHolder, position: Int) =
        holder.bind(stopRoutes.get(position))

    override fun getItemCount(): Int {
        return stopRoutes.size
    }

}
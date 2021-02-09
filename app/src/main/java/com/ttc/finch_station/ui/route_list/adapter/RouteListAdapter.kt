package com.ttc.finch_station.ui.route_list.adapter

import android.content.Context
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ttc.finch_station.R
import com.ttc.finch_station.data.model.business.Route
import com.ttc.finch_station.databinding.ItemRouteBinding

class RouteListAdapter(private var routes: List<Route>, private val context: Context) :
    RecyclerView.Adapter<RouteListAdapter.RouteViewHolder>() {

    lateinit var stopRouteListAdapter: StopRouteListAdapter

    inner class RouteViewHolder(val binding: ItemRouteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Route) {
            with(binding) {
                tvRouteName.text = item.name
                when (item.stopRoutes.size) {
                    0 -> btnDropdown.visibility = View.GONE
                    else -> {
                        btnDropdown.setOnClickListener {
                            if (rvStopTimes.visibility == View.GONE) {
                                TransitionManager.beginDelayedTransition(
                                    cardView,
                                    AutoTransition()
                                )
                                rvStopTimes.visibility = View.VISIBLE
                                btnDropdown.setBackgroundResource(R.drawable.ic_baseline_expand_less_24)
                            } else {
                                TransitionManager.beginDelayedTransition(
                                    cardView,
                                    AutoTransition()
                                )
                                rvStopTimes.visibility = View.GONE
                                btnDropdown.setBackgroundResource(R.drawable.ic_baseline_expand_more_24)
                            }
                        }
                    }
                }

                stopRouteListAdapter =
                    StopRouteListAdapter(
                        item.stopRoutes
                    )
                rvStopTimes.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = stopRouteListAdapter
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRouteBinding.inflate(layoutInflater, parent, false)

        return RouteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) =
        holder.bind(routes.get(position))

    override fun getItemCount(): Int {
        return routes.size
    }

}
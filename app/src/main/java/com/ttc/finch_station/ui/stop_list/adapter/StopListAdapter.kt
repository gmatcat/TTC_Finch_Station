package com.ttc.finch_station.ui.stop_list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ttc.finch_station.R
import com.ttc.finch_station.data.model.business.Stop
import com.ttc.finch_station.databinding.ItemStopBinding


class StopListAdapter(private var stops: List<Stop>, private val callback: OnViewRoutesCallback, private val context: Context) :
    RecyclerView.Adapter<StopListAdapter.StopViewHolder>() {

    inner class StopViewHolder(val binding: ItemStopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Stop) {
            with(binding) {
                tvStopName.text = item.name
                tvAgency.text = item.agency
                when (item.routes.size) {
                    0 -> {
                        btnViewRoutes.text = context.getText(R.string.no_available_routes)
                        btnViewRoutes.setTextColor(context.resources.getColor(R.color.red_fox))
                        btnViewRoutes.isClickable = false
                    }
                    else -> {
                        btnViewRoutes.text = context.getText(R.string.view_routes)
                        btnViewRoutes.setTextColor(context.resources.getColor(R.color.green_apple))
                        btnViewRoutes.setOnClickListener {
                            callback.onViewRoutes(item)
                        }
                        btnViewRoutes.isClickable = true
                    }
                }

            }
        }
    }

    companion object : DiffUtil.ItemCallback<Stop>() {
        override fun areItemsTheSame(oldItem: Stop, newItem: Stop): Boolean = oldItem === newItem
        override fun areContentsTheSame(oldItem: Stop, newItem: Stop): Boolean =
            oldItem.name == newItem.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemStopBinding.inflate(layoutInflater, parent, false)

        return StopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StopViewHolder, position: Int) =
        holder.bind(stops.get(position))

    override fun getItemCount(): Int {
        return stops.size
    }

    fun updateData(stops: List<Stop>?) {
        stops?.let {
            this.stops = it
        }
    }
}
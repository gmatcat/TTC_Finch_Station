package com.ttc.finch_station.data.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NetworkRoute(
    val name: String,
    val route_group_id: String,
    @SerializedName("stop_times")
    @Expose
    val networkStopTimes: List<NetworkStopTime>,
    val uri: String
)
package com.ttc.finch_station.data.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NetworkStation(
    val name: String,
    @SerializedName("stops")
    @Expose
    val networkStops: List<NetworkStop>,
    val time: Int,
    val uri: String
)
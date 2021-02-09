package com.ttc.finch_station.data.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NetworkStop(
    val agency: String,
    val name: String,
    @SerializedName("routes")
    @Expose
    val networkRoutes: List<NetworkRoute>,
    val uri: String
)
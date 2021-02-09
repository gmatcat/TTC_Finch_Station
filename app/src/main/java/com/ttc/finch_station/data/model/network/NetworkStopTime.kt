package com.ttc.finch_station.data.model.network

data class NetworkStopTime(
    val departure_time: String,
    val departure_timestamp: Int,
    val service_id: Int,
    val shape: String
)
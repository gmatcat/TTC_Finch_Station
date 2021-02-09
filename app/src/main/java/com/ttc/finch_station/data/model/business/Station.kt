package com.ttc.finch_station.data.model.business

data class Station(
    val name: String,
    val stops: List<Stop>,
    val time: Int
)
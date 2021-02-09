package com.ttc.finch_station.data.api

import com.ttc.finch_station.data.model.network.NetworkStation

interface StationApiHelper {

      suspend fun getFinchStationRoutes(): NetworkStation
}
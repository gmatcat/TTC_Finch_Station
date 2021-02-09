package com.ttc.finch_station.data.api

import com.ttc.finch_station.data.model.network.NetworkStation
import retrofit2.http.GET

interface ApiService {

    @GET("finch_station.json")
    suspend fun getFinchStationRoutes(): NetworkStation


}
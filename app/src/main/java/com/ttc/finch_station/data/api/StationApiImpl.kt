package com.ttc.finch_station.data.api

import javax.inject.Inject

class StationApiImpl

@Inject
constructor(
    private val apiService: ApiService
) : StationApiHelper {

    override suspend fun getFinchStationRoutes() = apiService.getFinchStationRoutes()

}
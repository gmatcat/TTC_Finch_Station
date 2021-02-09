package com.ttc.finch_station.repo.station

import com.ttc.finch_station.data.common.data.Resource
import com.ttc.finch_station.data.common.data.Status
import com.ttc.finch_station.data.model.business.Station
import javax.inject.Inject

class StationRepository
@Inject
constructor(
    private val stationLocalDataSource: StationDataSourceContract.Local,
    private val stationNetworkDataSource: StationDataSourceContract.Network
) : StationDataSourceContract.Repository {
    override suspend fun fetchFinchStationRoutesRemotely(): Resource<Station> {
        return stationNetworkDataSource.fetchFinchStationRoutes()
    }
}
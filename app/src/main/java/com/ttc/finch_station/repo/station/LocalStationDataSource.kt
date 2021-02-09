package com.ttc.finch_station.repo.station

import com.ttc.finch_station.data.common.data.Resource
import com.ttc.finch_station.data.model.business.Station
import javax.inject.Inject


class LocalStationDataSource
@Inject
constructor()
    : StationDataSourceContract.Local {
    override suspend fun fetchFinchStationRoutes(): Resource<Station> {
        TODO("Not yet implemented")
    }
    //TODO
    //No Local Data Source for Station Routes
}
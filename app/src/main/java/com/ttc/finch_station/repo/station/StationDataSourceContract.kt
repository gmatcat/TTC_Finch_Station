package com.ttc.finch_station.repo.station

import com.ttc.finch_station.data.common.data.Resource
import com.ttc.finch_station.data.model.business.Station

class StationDataSourceContract {
    interface Repository {
        suspend fun fetchFinchStationRoutesRemotely() : Resource<Station>
    }

    interface Local {
        suspend fun fetchFinchStationRoutes() : Resource<Station>
    }

    interface Network {
        suspend fun fetchFinchStationRoutes() : Resource<Station>
    }
}
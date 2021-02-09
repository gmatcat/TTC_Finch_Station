package com.ttc.finch_station.interactors

import com.ttc.finch_station.data.common.data.Resource
import com.ttc.finch_station.repo.station.StationDataSourceContract
import com.ttc.finch_station.data.model.business.Station
import javax.inject.Inject

class FetchStationRoutesUseCase
@Inject
constructor(
    private val repository: StationDataSourceContract.Repository
) {
    suspend operator fun invoke():
            Resource<Station> = repository.fetchFinchStationRoutesRemotely()
}
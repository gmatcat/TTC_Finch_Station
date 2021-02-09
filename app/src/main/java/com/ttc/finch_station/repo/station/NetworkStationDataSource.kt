package com.ttc.finch_station.repo.station

import android.accounts.NetworkErrorException
import com.ttc.finch_station.data.api.StationApiHelper
import com.ttc.finch_station.data.common.data.Resource
import com.ttc.finch_station.data.common.data.Status
import com.ttc.finch_station.data.model.mapper.StationDataMapper
import com.ttc.finch_station.data.model.business.Station
import java.io.IOException
import javax.inject.Inject

class NetworkStationDataSource
@Inject
constructor(
    private val apiHelperImpl: StationApiHelper,
    private val dtoMapper: StationDataMapper
) :
    StationDataSourceContract.Network {
    override suspend fun fetchFinchStationRoutes(): Resource<Station> {
        return try {
            Resource.success(dtoMapper.map(apiHelperImpl.getFinchStationRoutes()))
        } catch (throwable: Throwable) {
            val message = throwable.message
            when (throwable) {
                is IOException -> Resource.error(status = Status.IO_ERROR, msg = message.toString(), data = null)
                is NetworkErrorException -> {
                    Resource.error(status = Status.NETWORK_ERROR, msg = message.toString() , data = null)
                }
                else -> {
                    Resource.error(status = Status.GENERIC_ERROR, msg = message.toString(), data = null)
                }
            }
        }
    }
}

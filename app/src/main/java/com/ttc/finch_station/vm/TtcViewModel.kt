package com.ttc.finch_station.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ttc.finch_station.data.base.BaseViewModel
import com.ttc.finch_station.data.common.data.Resource
import com.ttc.finch_station.data.common.data.Status
import com.ttc.finch_station.data.common.logDebug
import com.ttc.finch_station.data.common.logError
import com.ttc.finch_station.data.model.business.Station
import com.ttc.finch_station.interactors.Interactors
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

public class TtcViewModel
@ViewModelInject public constructor(
    private val interactors: Interactors
) : BaseViewModel() {

    private val _stationRoutes = MutableLiveData<Resource<Station>>()

    val stationRoutes: LiveData<Resource<Station>>
        get() = _stationRoutes

    init {
        fetchStationRoutes()
    }

    fun fetchStationRoutes() {
        logDebug {}
        _stationRoutes.postValue((Resource.loading(data = null)))
        viewModelScope.launch {
            try {
                _stationRoutes.postValue(interactors.fetchStationRoutesUseCase())
            } catch (exception: CancellationException) {
                logError({}, exception)
                _stationRoutes.postValue(
                    (Resource.error(
                        status = Status.GENERIC_ERROR,
                        data = null,
                        msg = exception.message ?: "Cancellation Exception"
                    ))
                )
            } catch (exception:Exception){
                logError({}, exception)
                _stationRoutes.postValue(
                (Resource.error(
                    status = Status.GENERIC_ERROR,
                    data = null,
                    msg = exception.message ?: "Something Went Wrong"
                ))
            )

            }
        }
    }

}
package com.ttc.finch_station.interactors

import javax.inject.Inject

class Interactors @Inject constructor(
    val fetchStationRoutesUseCase: FetchStationRoutesUseCase
)

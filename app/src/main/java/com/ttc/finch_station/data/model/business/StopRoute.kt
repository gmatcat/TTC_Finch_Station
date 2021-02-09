package com.ttc.finch_station.data.model.business

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class StopRoute (
    val stopRouteName: String,
    val departureTimes: List<DepartureDateTime>
) : Parcelable
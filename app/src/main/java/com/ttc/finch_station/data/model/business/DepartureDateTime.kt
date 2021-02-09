package com.ttc.finch_station.data.model.business

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DepartureDateTime (
    val departureDate: String,
    val departureTimes: List<String>
) : Parcelable

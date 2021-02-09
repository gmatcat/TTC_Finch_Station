package com.ttc.finch_station.data.model.business

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DepartureDateTimePlaceholder (
    val departureDate: String,
    val departureTime: String
) : Parcelable
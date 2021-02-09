package com.ttc.finch_station.data.model.business

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StopTime(
    val departure_time: String,
    val departure_timestamp: Int,
    val service_id: Int,
    val shape: String,
    val departure_date_time_text: String
) : Parcelable
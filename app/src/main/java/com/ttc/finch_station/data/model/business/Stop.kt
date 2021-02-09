package com.ttc.finch_station.data.model.business

import android.os.Parcelable
import com.ttc.finch_station.data.model.network.NetworkRoute
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Stop(
    val agency: String,
    val name: String,
    val routes: List<Route>
) : Parcelable
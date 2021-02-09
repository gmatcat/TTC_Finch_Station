package com.ttc.finch_station.data.model.business

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Route(
    val name: String,
    val route_group_id: String,
    val stopRoutes: List<StopRoute>
) : Parcelable
package com.ttc.finch_station.ui.stop_list.adapter

import com.ttc.finch_station.data.model.business.Route
import com.ttc.finch_station.data.model.business.Stop

interface OnViewRoutesCallback {
    fun onViewRoutes(stop: Stop)
}

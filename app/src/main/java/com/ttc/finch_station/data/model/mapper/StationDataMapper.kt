package com.ttc.finch_station.data.model.mapper

import com.ttc.finch_station.data.common.mapper.core.ListMapper
import com.ttc.finch_station.data.common.mapper.core.Mapper
import com.ttc.finch_station.data.model.business.*
import com.ttc.finch_station.data.model.network.NetworkRoute
import com.ttc.finch_station.data.model.network.NetworkStation
import com.ttc.finch_station.data.model.network.NetworkStop
import com.ttc.finch_station.data.model.network.NetworkStopTime
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject


class StationDataMapper
@Inject
constructor(
    private val stopListDataMapper: ListMapper<NetworkStop, Stop>
) : Mapper<NetworkStation, Station> {

    override fun map(input: NetworkStation): Station {
        return Station(
            name = input.name,
            time = input.time,
            stops = stopListDataMapper.map(input.networkStops).sortedBy {
                stop -> stop.routes.isEmpty()
            }
            //Removed URI in business object as not used in UI
            //uri = input.uri,
        )
    }
}

class StopDataMapper
@Inject
constructor(
    private val routeListDataMapper: ListMapper<NetworkRoute, Route>
) :
   ListMapper<NetworkStop, Stop> {

    override fun map(input: List<NetworkStop>): List<Stop> {
        return input.map { it ->
            Stop(
                agency = it.agency,
                name = it.name,
                routes = routeListDataMapper.map(it.networkRoutes)
                //Removed URI in business object as not used in UI
                //uri = input.uri,
            )
        }.orEmpty()
    }

}

class RouteDataMapper
@Inject
constructor(
    private val stopRouteDataMapper: ListMapper<NetworkStopTime, StopRoute>
) :
    ListMapper<NetworkRoute, Route> {

    override fun map(input: List<NetworkRoute>): List<Route> {
        return input.map { it ->
            Route(
                name = it.name,
                route_group_id = it.route_group_id,
                stopRoutes = stopRouteDataMapper.map(it.networkStopTimes)
                //Removed URI in business object as not used in UI
                //uri = input.uri,
            )
        }!!
    }
}


class StopRouteDataMapper
@Inject
constructor() :
    ListMapper<NetworkStopTime, StopRoute> {

    override fun map(input: List<NetworkStopTime>): List<StopRoute> {
        return input.groupBy {
            it.shape
        }.entries.map { (shape, group) ->
            StopRoute(
                shape, group.map {
                    DepartureDateTimePlaceholder(
                    convertTimeStampMillisToDateString(it.departure_timestamp),
                    convertTimeStampMillisToTimeString(it.departure_timestamp)
                    )
                }.groupBy { it.departureDate }.entries.map {
                    (date, time) ->
                    DepartureDateTime (
                        date,
                        time.map {
                            it.departureTime
                        }
                    )
                }
            )
        }
    }

}


var zoneToronto = DateTimeZone.forID("America/Toronto")

fun convertTimeStampMillisToDateString(timestamp: Int) : String {
    val jodatime = DateTime(timestamp * 1000L)
    val dtfOut: DateTimeFormatter = DateTimeFormat.forPattern("MMMMM dd, yyyy")
        .withZone(zoneToronto)
    return dtfOut.print(jodatime)
}

fun convertTimeStampMillisToTimeString(timestamp: Int) : String {
    val jodatime = DateTime(timestamp * 1000L)
    val dtfOut: DateTimeFormatter = DateTimeFormat.forPattern("hh:mm a")
        .withZone(zoneToronto)
    return dtfOut.print(jodatime)
}

package com.ttc.finch_station.data.api

import com.ttc.finch_station.data.common.data.Resource
import com.ttc.finch_station.data.model.business.Station
import com.ttc.finch_station.data.model.network.NetworkStation
import com.ttc.finch_station.repo.station.StationDataSourceContract
import com.ttc.finch_station.repo.station.StationRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(JUnit4ClassRunner::class)
public class StationApiImplTest {

    lateinit var SUT: StationApiImpl

    @MockK
    lateinit var apiService: ApiService

    private val STATION_NAME = "StationName"
    private val URI = "uri"
    private val TIME = 123456
    private var NETWORK_STATION: NetworkStation = NetworkStation(
        name = STATION_NAME, networkStops = listOf(),
        time = TIME, uri = URI
    )


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        SUT = StationApiImpl(apiService)
    }



    @Test
    fun `when invoked fetchFinchStationRoutesRemotely verify fetchFinchStationRoutes invoked once`() = runBlocking {
        //Given
        success()

        //When
        SUT.getFinchStationRoutes()

        //Then
        val result = verify(atLeast = 1){
            runBlocking {
                apiService.getFinchStationRoutes()
            }
        }
    }

    @Test
    fun `when success fetchFinchStationRoutesRemotely return NETWORK_STATION`() = runBlocking {
        //Given
        success()

        //When
        SUT.getFinchStationRoutes()

        //Then
        val result = verify(atLeast = 1){
            runBlocking {
                apiService.getFinchStationRoutes()
            }
        }

    }

    private fun success() {
        every {
            runBlocking {
                apiService.getFinchStationRoutes()
            }
        } returns NETWORK_STATION
    }

}

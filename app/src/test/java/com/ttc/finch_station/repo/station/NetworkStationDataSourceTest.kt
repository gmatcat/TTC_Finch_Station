package com.ttc.finch_station.repo.station

import android.accounts.NetworkErrorException
import com.ttc.finch_station.data.api.StationApiImpl
import com.ttc.finch_station.data.common.data.Status
import com.ttc.finch_station.data.model.business.Station
import com.ttc.finch_station.data.model.mapper.StationDataMapper
import com.ttc.finch_station.data.model.network.NetworkStation
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertNull
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(JUnit4ClassRunner::class)
class NetworkStationDataSourceTest {

    lateinit var SUT: NetworkStationDataSource

    @MockK
    lateinit var mockApiHelperImpl: StationApiImpl

    @MockK
    lateinit var mockDtoMapper: StationDataMapper


    private val STATION_NAME = "StationName"
    private val URI = "uri"
    private val TIME = 123456
    private var STATION: Station  = Station(name = STATION_NAME, stops = listOf(), time = TIME)
    private var NETWORK_STATION: NetworkStation = NetworkStation(
        name = STATION_NAME, networkStops = listOf(),
        time = TIME, uri = URI
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { mockDtoMapper.map(any()) } returns STATION!!
        SUT = NetworkStationDataSource(mockApiHelperImpl, mockDtoMapper)
    }


    @Test
    fun `when success fetchFinchStationRoutes then return station`() = runBlocking {
        //Given
        success()

        //When
        val result = SUT.fetchFinchStationRoutes()

        //Then
        assertThat(result.status, `is`(Status.SUCCESS))
        assertThat(result.data, `is`(STATION))
    }

    @Test
    fun `when httpException fetchFinchStationRoutes then return failure`() = runBlocking {
        //Given
        networkException()

        //When
        val result = SUT.fetchFinchStationRoutes()

        //Then
        assertThat(result.status, `is`(Status.NETWORK_ERROR))
        assertNull(result.data)
    }

    @Test
    fun `when ioException fetchFinchStationRoutes then return failure`() = runBlocking {
        //Given
        ioException()

        //When
        val result = SUT.fetchFinchStationRoutes()

        //Then
        assertThat(result.status, `is`(Status.IO_ERROR))
        assertNull(result.data)
    }


    @Test
    fun `when genericException fetchFinchStationRoutes then return failure`() = runBlocking {
        //Given
        genericException()

        //When
        val result = SUT.fetchFinchStationRoutes()

        //Then
        assertThat(result.status, `is`(Status.GENERIC_ERROR))
        assertNull(result.data)
    }


    private suspend fun success() {
        every { runBlocking { mockApiHelperImpl.getFinchStationRoutes() } } returns NETWORK_STATION
    }


    suspend fun networkException() {
        every {
            runBlocking { mockApiHelperImpl.getFinchStationRoutes() }
        } throws NetworkErrorException()
    }

    suspend fun ioException() {
        every { runBlocking { mockApiHelperImpl.getFinchStationRoutes() } } throws IOException()
    }

    suspend fun genericException() {
        every { runBlocking { mockApiHelperImpl.getFinchStationRoutes() } } throws Exception()
    }

}

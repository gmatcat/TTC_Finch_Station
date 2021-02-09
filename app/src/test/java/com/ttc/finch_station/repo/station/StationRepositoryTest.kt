package com.ttc.finch_station.repo.station

import com.ttc.finch_station.data.api.StationApiImpl
import com.ttc.finch_station.data.common.data.Resource
import com.ttc.finch_station.data.model.business.Station
import com.ttc.finch_station.data.model.mapper.StationDataMapper
import com.ttc.finch_station.interactors.FetchStationRoutesUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(JUnit4ClassRunner::class)
class StationRepositoryTest {


    lateinit var SUT: StationRepository

    @MockK
    lateinit var mockStationNetworkDataSource: StationDataSourceContract.Network

    @MockK
    lateinit var mockStationLocalDataSource: StationDataSourceContract.Local


    private val STATION_NAME = "StationName"
    private val TIME = 123456
    private var STATION: Station = Station(name = STATION_NAME, stops = listOf(), time = TIME)


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        SUT = StationRepository(mockStationLocalDataSource, mockStationNetworkDataSource)
        every {
            runBlocking {
                mockStationNetworkDataSource.fetchFinchStationRoutes()
            }
        } returns Resource.success(STATION)
    }



    @Test
    fun `when invoked fetchFinchStationRoutesRemotely verify fetchFinchStationRoutes invoked once`() = runBlocking {
        //Given

        //When
        SUT.fetchFinchStationRoutesRemotely()

        //Then
        val result = verify(atLeast = 1){
            runBlocking {
                mockStationNetworkDataSource.fetchFinchStationRoutes()
            }
        }

    }


}

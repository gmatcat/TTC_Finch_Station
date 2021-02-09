package com.ttc.finch_station.interactors

import com.ttc.finch_station.data.common.data.Resource
import com.ttc.finch_station.data.common.data.Status
import com.ttc.finch_station.data.model.business.Station
import com.ttc.finch_station.repo.station.StationDataSourceContract
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import junit.framework.Assert.assertNull
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*

@RunWith(JUnit4ClassRunner::class)
public class FetchStationRoutesUseCaseTest {

    lateinit var SUT: FetchStationRoutesUseCase

    @MockK
    lateinit var mockRepo: StationDataSourceContract.Repository

    private val STATION_NAME = "StationName"
    private val TIME = 123456
    private val STATION: Station  = Station(name = STATION_NAME, stops = listOf(), time = TIME)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        SUT = FetchStationRoutesUseCase(mockRepo)
    }



    @Test
    fun `when success on fetchStationRoutesUseCase return station`() = runBlocking {
        //Given
        success()

        //When
        val response = SUT.invoke()

        //Then
        assertThat(response.status, `is`(Status.SUCCESS))
        assertThat(response.data, `is`(STATION))
    }

    @Test
    fun `when failure on fetchStationRoutesUseCase return failure`() = runBlocking {
        //Given
        error()

        //When
        val response = SUT.invoke()

        //Then
        assertThat(response.status, `is`(Status.GENERIC_ERROR))
        assertNull(response.data)
    }


    @Test
    fun `when invoked fetchStationRoutesUseCase verify fetchFinchStationRoutesRemotely invoked once`() = runBlocking {
        //Given
        success()

        //When
        SUT.invoke()

        //Then
        val result = verify(atLeast = 1){
            runBlocking {
                mockRepo.fetchFinchStationRoutesRemotely()
            }
        }
    }


    private suspend fun success() {
        every { runBlocking { mockRepo.fetchFinchStationRoutesRemotely() } } returns Resource.success(STATION)
    }

    suspend fun error() {
        every {
            runBlocking { mockRepo.fetchFinchStationRoutesRemotely() }
        } returns Resource(Status.GENERIC_ERROR, any(), anyString())

    }
}




package com.ttc.finch_station

import com.ttc.finch_station.data.api.StationApiImpl
import com.ttc.finch_station.data.api.StationApiImplTest
import com.ttc.finch_station.interactors.FetchStationRoutesUseCaseTest
import com.ttc.finch_station.repo.station.NetworkStationDataSourceTest
import com.ttc.finch_station.repo.station.StationRepositoryTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    StationApiImplTest::class,
    FetchStationRoutesUseCaseTest::class,
    NetworkStationDataSourceTest::class,
    StationRepositoryTest::class
)
class UnitTestSuite
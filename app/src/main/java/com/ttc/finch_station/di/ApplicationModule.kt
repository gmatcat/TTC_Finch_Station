package com.ttc.finch_station.di

import com.ttc.finch_station.data.api.StationApiHelper
import com.ttc.finch_station.data.model.mapper.RouteDataMapper
import com.ttc.finch_station.data.model.mapper.StationDataMapper
import com.ttc.finch_station.data.model.mapper.StopDataMapper
import com.ttc.finch_station.data.model.mapper.StopRouteDataMapper
import com.ttc.finch_station.repo.station.LocalStationDataSource
import com.ttc.finch_station.repo.station.NetworkStationDataSource
import com.ttc.finch_station.repo.station.StationDataSourceContract
import com.ttc.finch_station.repo.station.StationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    //    @Provides
//    fun provideStationUseCase(forexPairRepository: ForexPairRepository): ForexPairUseCase =
//        ForexPairUseCase(forexPairRepository)
//
    @Provides
    fun provideStationRepository(
        stationLocalDataSource: StationDataSourceContract.Local,
        stationNetworkDataSource: StationDataSourceContract.Network
    ): StationDataSourceContract.Repository =
        StationRepository(stationLocalDataSource, stationNetworkDataSource)

    @Provides
    fun provideStationLocalDataSource(): StationDataSourceContract.Local
            = LocalStationDataSource()

    @Provides
    fun provideStationRemoteDataSource(
        stationApiHelper: StationApiHelper,
        stationDataMapper: StationDataMapper
    ): StationDataSourceContract.Network  =
        NetworkStationDataSource(stationApiHelper, stationDataMapper)


    @Provides
    fun provideStationDataMapper(
        stopListDataMapper: StopDataMapper
    ) =
        StationDataMapper(
            stopListDataMapper
        )

    @Provides
    fun provideRouteListDataMapper(
        routeListDataMapper: RouteDataMapper
    ) =
        StopDataMapper(
            routeListDataMapper
        )

    @Provides
    fun provideRouteDataMapper(
        stopRouteListDataMapper: StopRouteDataMapper
    ) =
        RouteDataMapper(
            stopRouteListDataMapper
        )

    @Provides
    fun provideStopTimeDataMapper(
    ): StopRouteDataMapper =
        StopRouteDataMapper()



}
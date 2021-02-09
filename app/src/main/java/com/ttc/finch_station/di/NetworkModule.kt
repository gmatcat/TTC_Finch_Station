package com.ttc.finch_station.di

import android.content.Context
import com.google.gson.Gson
import com.ttc.finch_station.BuildConfig.BASE_URL
import com.ttc.finch_station.data.api.ApiService
import com.ttc.finch_station.data.api.StationApiHelper
import com.ttc.finch_station.data.api.StationApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideStationApiHelper(stationApiImpl: StationApiImpl): StationApiHelper = stationApiImpl

    @Provides
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }


    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .cache(cache)
        return client.build()
    }

    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }



    @Provides
    fun providesOkhttpCache(@ApplicationContext context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        return Cache(context.cacheDir, cacheSize.toLong())
    }

    @Provides
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

}


package com.ttc

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object TestAppModule {

    @Provides
    fun provideString(): String = "Test String"
}
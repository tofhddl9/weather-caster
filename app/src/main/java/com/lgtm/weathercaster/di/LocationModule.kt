package com.lgtm.weathercaster.di

import com.lgtm.weathercaster.utils.LocationProvider
import com.lgtm.weathercaster.utils.LocationProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationProvider(
        locationProvider: LocationProviderImpl
    ): LocationProvider
}
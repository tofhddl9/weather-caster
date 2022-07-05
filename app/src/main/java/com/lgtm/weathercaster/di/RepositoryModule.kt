package com.lgtm.weathercaster.di

import com.lgtm.weathercaster.data.WeatherDataSource
import com.lgtm.weathercaster.data.WeatherRepositoryImpl
import com.lgtm.weathercaster.data.local.WeatherDatabase
import com.lgtm.weathercaster.data.local.WeatherLocalDataSource
import com.lgtm.weathercaster.data.remote.WeatherRemoteDataSource
import com.lgtm.weathercaster.data.remote.WeatherService
import com.lgtm.weathercaster.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

// TODO. Use @Binds of Hilt

@Qualifier
annotation class LocalDataSource

@Qualifier
annotation class RemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherLocalDataSource(
        database: WeatherDatabase,
        ioDispatcher: CoroutineDispatcher
    ): WeatherDataSource {
        return WeatherLocalDataSource(database.weatherDao(), ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideWeatherRemoteDataSource(
        apiService: WeatherService,
    ): WeatherDataSource {
        return WeatherRemoteDataSource(apiService)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(
        @LocalDataSource weatherLocalDataSource: WeatherDataSource,
        @RemoteDataSource weatherRemoteWeatherDataSource: WeatherDataSource,
        ioDispatcher: CoroutineDispatcher
    ): WeatherRepository {
        return WeatherRepositoryImpl(weatherLocalDataSource, weatherRemoteWeatherDataSource, ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @LocalDataSource
    @Binds
    abstract fun bindLocalDataSource(impl: WeatherLocalDataSource): WeatherDataSource

}

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @RemoteDataSource
    @Binds
    abstract fun bindRemoteDataSource(impl: WeatherRemoteDataSource): WeatherDataSource

}

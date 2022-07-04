package com.lgtm.weathercaster.di

import android.content.Context
import androidx.room.Room
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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

// TODO. Use @Binds of Hilt
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

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
    fun provideWeatherApi(): WeatherService {
        return Retrofit.Builder()
            .baseUrl(WeatherService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }).build()
            )
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(
        weatherLocalDataSource: WeatherDataSource,
        weatherRemoteWeatherDataSource: WeatherDataSource,
        ioDispatcher: CoroutineDispatcher
    ): WeatherRepository {
        return WeatherRepositoryImpl(weatherLocalDataSource, weatherRemoteWeatherDataSource, ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            WeatherDatabase::class.java,
            "Weather.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
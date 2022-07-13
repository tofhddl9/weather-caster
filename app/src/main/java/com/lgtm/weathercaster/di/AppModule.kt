package com.lgtm.weathercaster.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.lgtm.weathercaster.data.WeatherDataSource
import com.lgtm.weathercaster.domain.WeatherRepository
import com.lgtm.weathercaster.data.WeatherRepositoryImpl
import com.lgtm.weathercaster.data.local.WeatherDao
import com.lgtm.weathercaster.data.local.WeatherDatabase
import com.lgtm.weathercaster.data.local.WeatherLocalDataSource
import com.lgtm.weathercaster.data.local.converter.WeatherDataListTypeConverter
import com.lgtm.weathercaster.data.local.converter.WeatherDataTypeConverter
import com.lgtm.weathercaster.data.remote.WeatherService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherService {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory()) // 코틀린에서 JsonAdapter 를 생성하기 위한 팩토리 객체
            .build()

        return Retrofit.Builder()
            .baseUrl(WeatherService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(
                OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }).build()
            )
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): WeatherDatabase {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory()) // 코틀린에서 JsonAdapter 를 생성하기 위한 팩토리 객체
            .build()

        return Room.databaseBuilder(
                context.applicationContext,
                WeatherDatabase::class.java,
                "Weather.db")
            .addTypeConverter(WeatherDataTypeConverter(moshi))
            .addTypeConverter(WeatherDataListTypeConverter(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun providePlantDao(appDatabase: WeatherDatabase): WeatherDao {
        return appDatabase.weatherDao()
    }

    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

}
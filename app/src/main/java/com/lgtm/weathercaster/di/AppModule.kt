package com.lgtm.weathercaster.di

import android.content.Context
import androidx.room.Room
import com.lgtm.weathercaster.data.WeatherDataSource
import com.lgtm.weathercaster.domain.WeatherRepository
import com.lgtm.weathercaster.data.WeatherRepositoryImpl
import com.lgtm.weathercaster.data.local.WeatherDatabase
import com.lgtm.weathercaster.data.local.WeatherLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFooLocalDataSource(
        database: WeatherDatabase,
        ioDispatcher: CoroutineDispatcher
    ): WeatherDataSource {
        return WeatherLocalDataSource(database.weatherDao(), ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideFooRepository(
        localTasksDataSource: WeatherDataSource,
        ioDispatcher: CoroutineDispatcher
    ): WeatherRepository {
        return WeatherRepositoryImpl(localTasksDataSource, ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            WeatherDatabase::class.java,
            "Foo.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}
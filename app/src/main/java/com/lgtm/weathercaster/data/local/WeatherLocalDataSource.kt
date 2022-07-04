package com.lgtm.weathercaster.data.local

import com.lgtm.weathercaster.data.WeatherDataSource
import com.lgtm.weathercaster.data.WeatherVO
import com.lgtm.weathercaster.data.mapper.mapToWeatherVO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class WeatherLocalDataSource(
    private val weatherDao: WeatherDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : WeatherDataSource {

    override suspend fun getCurrentWeather(): WeatherVO? {
        return weatherDao.getCurrentWeather()?.mapToWeatherVO()
    }

}
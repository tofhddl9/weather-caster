package com.lgtm.weathercaster.data.local

import com.lgtm.weathercaster.data.WeatherDataSource
import com.lgtm.weathercaster.data.vo.WeatherVO
import com.lgtm.weathercaster.data.mapper.mapToWeatherVO
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class WeatherLocalDataSource @Inject constructor(
    private val weatherDao: WeatherDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : WeatherDataSource {

    override suspend fun getCurrentWeather(latitude: Double, longitude: Double): WeatherVO? {
        return weatherDao.getCurrentWeather()?.mapToWeatherVO()
    }

}
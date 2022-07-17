package com.lgtm.weathercaster.data.local

import com.lgtm.weathercaster.data.WeatherDataSource
import com.lgtm.weathercaster.data.mapper.mapToWeatherEntity
import com.lgtm.weathercaster.domain.vo.WeatherVO
import com.lgtm.weathercaster.data.mapper.mapToWeatherVO
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherLocalDataSource @Inject constructor(
    private val weatherDao: WeatherDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : WeatherDataSource {

    override suspend fun getCurrentWeather(latitude: Double, longitude: Double): WeatherVO? = withContext(ioDispatcher) {
        weatherDao.getCurrentWeather()?.mapToWeatherVO()
    }

    override suspend fun insertCurrentWeather(weatherVO: WeatherVO) {
        weatherDao.insertCurrentWeather(weatherVO.mapToWeatherEntity())
    }

    override suspend fun clearCurrentWeather() = withContext(ioDispatcher) {
        weatherDao.clearCurrentWeather()
    }

}
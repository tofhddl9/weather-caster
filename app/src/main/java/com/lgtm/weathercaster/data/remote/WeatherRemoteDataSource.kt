package com.lgtm.weathercaster.data.remote

import com.lgtm.weathercaster.data.WeatherDataSource
import com.lgtm.weathercaster.data.WeatherVO
import com.lgtm.weathercaster.data.mapper.mapToWeatherVO

private const val KEY = "1e30727449d9657c7b8e82b58b9b49b3"

class WeatherRemoteDataSource(
    private val weatherService: WeatherService,
) : WeatherDataSource {

    override suspend fun getCurrentWeather(lat: Float, lon: Float): WeatherVO? {
        return weatherService.getWeather(lat, lon, KEY)?.mapToWeatherVO()
    }

}

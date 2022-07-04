package com.lgtm.weathercaster.data

interface WeatherDataSource {

    suspend fun getCurrentWeather(
        lat: Float,
        lon: Float,
    ): WeatherVO?

}
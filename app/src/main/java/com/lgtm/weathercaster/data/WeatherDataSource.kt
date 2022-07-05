package com.lgtm.weathercaster.data

interface WeatherDataSource {

    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
    ): WeatherVO?

}
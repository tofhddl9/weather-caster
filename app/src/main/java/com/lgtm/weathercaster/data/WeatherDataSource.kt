package com.lgtm.weathercaster.data

interface WeatherDataSource {

    suspend fun getCurrentWeather(): WeatherVO

}
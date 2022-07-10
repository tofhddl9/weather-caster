package com.lgtm.weathercaster.data

import com.lgtm.weathercaster.data.vo.WeatherVO

interface WeatherDataSource {

    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
    ): WeatherVO?

}
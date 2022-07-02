package com.lgtm.weathercaster.domain

import com.lgtm.weathercaster.data.WeatherVO

interface WeatherRepository {

    suspend fun getCurrentWeather(): WeatherVO

}
package com.lgtm.weathercaster.data.local

import androidx.room.Dao

@Dao
interface WeatherDao {

    fun getCurrentWeather(): WeatherEntity?

}
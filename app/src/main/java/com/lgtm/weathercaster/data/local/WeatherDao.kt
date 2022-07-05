package com.lgtm.weathercaster.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun getCurrentWeather(): WeatherEntity?

}
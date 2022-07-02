package com.lgtm.weathercaster.data.local

import androidx.room.Entity

@Entity(tableName = "weather")
data class WeatherEntity(
    val foo: Int
)
package com.lgtm.weathercaster.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lgtm.weathercaster.data.remote.WeatherInfo

@Database(entities = [WeatherInfo::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}
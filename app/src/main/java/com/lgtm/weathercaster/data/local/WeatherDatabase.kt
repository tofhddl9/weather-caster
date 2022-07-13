package com.lgtm.weathercaster.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lgtm.weathercaster.data.local.converter.WeatherDataListTypeConverter
import com.lgtm.weathercaster.data.local.converter.WeatherDataTypeConverter
import com.lgtm.weathercaster.data.local.converter.WeatherEntityTypeConverter

@TypeConverters(
    value = [
        WeatherEntityTypeConverter::class,
        WeatherDataListTypeConverter::class,
        WeatherDataTypeConverter::class,
    ]
)
@Database(entities = [WeatherEntity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}
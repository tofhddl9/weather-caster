package com.lgtm.weathercaster.data.local

import com.lgtm.weathercaster.data.WeatherDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class WeatherLocalDataSource(
    private val weatherDao: WeatherDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : WeatherDataSource {

}
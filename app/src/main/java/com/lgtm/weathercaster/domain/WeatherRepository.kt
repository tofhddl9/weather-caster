package com.lgtm.weathercaster.domain

import com.lgtm.weathercaster.data.vo.WeatherVO
import com.lgtm.weathercaster.utils.Response
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        fetchFromRemote: Boolean,
    ): Flow<Response<WeatherVO>>

}
package com.lgtm.weathercaster.data.mapper

import com.lgtm.weathercaster.data.WeatherVO
import com.lgtm.weathercaster.data.local.WeatherEntity
import com.lgtm.weathercaster.data.remote.WeatherDTO

fun WeatherEntity.mapToWeatherVO() = WeatherVO(
    foo = 1
)

fun WeatherVO.mapToWeatherEntity() = WeatherEntity(
    foo = 1
)

fun WeatherDTO.mapToWeatherVO() = WeatherVO(
    foo = 1
)

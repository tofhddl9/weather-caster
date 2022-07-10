package com.lgtm.weathercaster.data.vo

import com.lgtm.weathercaster.presentation.widgets.WeatherViewType

data class HourlyWeatherVO(
    override val itemId: Int = WeatherViewType.HOURLY_WEATHER_SUMMARY,
    val hourlyWeathers: List<WeatherDataVO>? = null,
) : WeatherItemVO

internal fun WeatherVO.mapToHourlyWeatherVO() = HourlyWeatherVO(
    hourlyWeathers = hourlyWeathers
)
package com.lgtm.weathercaster.data.vo.item

import com.lgtm.weathercaster.data.vo.WeatherVO
import com.lgtm.weathercaster.presentation.widgets.WeatherViewType

data class CurrentWeatherSummaryVO(
    override val itemId: Int = WeatherViewType.CURRENT_WEATHER_SUMMARY,
    val temperature: Float = 0f,
    val description: String? = null,
    val hourlyRain: Float = 0f,
    val uvi: Float = 0f,
) : WeatherItemVO

internal fun WeatherVO.mapToCurrentWeatherSummaryVO() = CurrentWeatherSummaryVO(
    temperature = current?.temperature ?: 0f,
    description = current?.weatherMetaData?.description,
    hourlyRain = current?.precipitation ?: 0f,
    uvi = current?.uvi ?: 0f,
)

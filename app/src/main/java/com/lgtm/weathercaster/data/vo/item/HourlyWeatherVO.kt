package com.lgtm.weathercaster.data.vo.item

import com.lgtm.weathercaster.data.vo.WeatherDataVO
import com.lgtm.weathercaster.data.vo.WeatherVO
import com.lgtm.weathercaster.presentation.widgets.WeatherViewType

data class HourlyWeatherVO(
    override val itemId: Int = WeatherViewType.HOURLY_WEATHER_SUMMARY,
    val title: String? = null,
    val hourlyWeathers: List<WeatherDataVO>? = null,
) : WeatherItemVO

internal fun WeatherVO.mapToHourlyWeatherVO() = HourlyWeatherVO(
    title = "시간별 예보",
    hourlyWeathers = hourlyWeathers,
)
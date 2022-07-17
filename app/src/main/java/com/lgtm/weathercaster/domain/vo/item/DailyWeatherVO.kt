package com.lgtm.weathercaster.domain.vo.item

import com.lgtm.weathercaster.domain.vo.WeatherDataVO
import com.lgtm.weathercaster.domain.vo.WeatherVO
import com.lgtm.weathercaster.presentation.widgets.WeatherViewType

data class DailyWeatherVO(
    override val itemId: Int = WeatherViewType.DAILY_WEATHER_SUMMARY,
    val title: String? = null,
    val dailyWeathers: List<WeatherDataVO>? = null,
) : WeatherItemVO

internal fun WeatherVO.mapToDailyWeatherVO() = DailyWeatherVO(
    title = "${dailyWeathers?.size}일간의 일기예보",
    dailyWeathers = dailyWeathers
)

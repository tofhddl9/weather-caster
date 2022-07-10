package com.lgtm.weathercaster.data.vo.item

import com.lgtm.weathercaster.data.vo.WeatherDataVO
import com.lgtm.weathercaster.data.vo.WeatherVO
import com.lgtm.weathercaster.presentation.widgets.WeatherViewType

data class DailyWeatherVO(
    override val itemId: Int = WeatherViewType.DAILY_WEATHER_SUMMARY,
    val dailyWeathers: List<WeatherDataVO>? = null,
) : WeatherItemVO

internal fun WeatherVO.mapToDailyWeatherVO() = DailyWeatherVO(
    dailyWeathers = dailyWeathers
)

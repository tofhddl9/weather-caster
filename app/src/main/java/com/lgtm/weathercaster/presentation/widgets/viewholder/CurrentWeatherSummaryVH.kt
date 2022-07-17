package com.lgtm.weathercaster.presentation.widgets.viewholder

import androidx.viewbinding.ViewBinding
import com.lgtm.weathercaster.domain.vo.item.CurrentWeatherSummaryVO
import com.lgtm.weathercaster.domain.vo.item.WeatherItemVO
import com.lgtm.weathercaster.databinding.ViewCurrentWeatherSummaryBinding
import com.lgtm.weathercaster.presentation.widgets.WeatherViewType
import kotlin.math.roundToInt

class CurrentWeatherSummaryVH(
    private val binding: ViewBinding,
) : WeatherVH(binding) {

    override val viewType: Int = WeatherViewType.CURRENT_WEATHER_SUMMARY

    override fun bindData(data: WeatherItemVO) = with(binding as ViewCurrentWeatherSummaryBinding) {
        data as CurrentWeatherSummaryVO

        temperature.text = "${data.temperature.roundToInt()}\u00B0"
        weatherInfo.text = "${data.description}"
        airCondition.text = "시간당 강수량 : ${data.hourlyRain}mm"
        updateTime.text = "마지막 업데이트 : ${data.lastUpdate}"
    }

}
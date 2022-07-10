package com.lgtm.weathercaster.presentation.widgets.viewholder

import androidx.viewbinding.ViewBinding
import com.lgtm.weathercaster.data.vo.item.WeatherItemVO
import com.lgtm.weathercaster.databinding.ViewWeatherSummaryBinding
import com.lgtm.weathercaster.presentation.widgets.WeatherViewType

class DailyWeatherSummaryVH(
    private val binding: ViewBinding,
) : WeatherVH(binding) {

    override val viewType: Int = WeatherViewType.DAILY_WEATHER_SUMMARY

    override fun bindData(data: WeatherItemVO) = with(binding as ViewWeatherSummaryBinding) {
        temperature.text= "32도"
        weatherInfo.text = "123"
        airCondition.text = "Daily"
    }

}

package com.lgtm.weathercaster.presentation.widgets.viewholder

import androidx.viewbinding.ViewBinding
import com.lgtm.weathercaster.data.vo.item.WeatherItemVO
import com.lgtm.weathercaster.databinding.ViewWeatherSummaryBinding
import com.lgtm.weathercaster.presentation.widgets.WeatherViewType

class HourlyWeatherSummaryVH(
    private val binding: ViewBinding,
) : WeatherVH(binding) {

    override val viewType: Int = WeatherViewType.HOURLY_WEATHER_SUMMARY

    override fun bindData(data: WeatherItemVO) = with(binding as ViewWeatherSummaryBinding) {
        temperature.text= "08도"
        weatherInfo.text = "1234"
        airCondition.text = "Hourly"
    }

}

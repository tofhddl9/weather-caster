package com.lgtm.weathercaster.presentation.widgets.viewholder

import androidx.viewbinding.ViewBinding
import com.lgtm.weathercaster.data.vo.CurrentWeatherSummaryVO
import com.lgtm.weathercaster.data.vo.WeatherItemVO
import com.lgtm.weathercaster.databinding.ViewWeatherSummaryBinding
import com.lgtm.weathercaster.presentation.widgets.WeatherViewType

class CurrentWeatherSummaryVH(
    private val binding: ViewBinding,
) : WeatherVH(binding) {

    override val viewType: Int = WeatherViewType.CURRENT_WEATHER_SUMMARY

    override fun bindData(data: WeatherItemVO) = with(binding as ViewWeatherSummaryBinding) {
        temperature.text= "24도"
        weatherInfo.text = "Info"
        airCondition.text = "Air"
    }

}
package com.lgtm.weathercaster.presentation.widgets.viewholder

import android.view.View
import androidx.viewbinding.ViewBinding
import com.lgtm.weathercaster.data.WeatherVO
import com.lgtm.weathercaster.databinding.ViewWeatherSummaryBinding
import com.lgtm.weathercaster.presentation.widgets.WeatherViewType

class WeatherSummaryVH(
    private val binding: ViewBinding,
) : WeatherVH(binding) {

    override val viewType: Int = WeatherViewType.WEATHER_VIEW_TYPE_1

    override fun bindData(data: WeatherVO) = with(binding as ViewWeatherSummaryBinding) {
        temperature.text= "24도"
        weatherInfo.text = "Info"
        airCondition.text = "Air"
    }

}
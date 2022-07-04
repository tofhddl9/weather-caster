package com.lgtm.weathercaster.presentation

import com.lgtm.weathercaster.data.WeatherVO

data class WeatherUiState(
    val weatherInfo: WeatherVO? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)
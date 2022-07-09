package com.lgtm.weathercaster.presentation

import com.lgtm.weathercaster.data.WeatherVO

// ViewModel 에서 API를 통해 전체적인 데이터를 받아옴
// ViewModel 에서 무언가에서 화면에 그릴 List 를 구성함 (일단 서버 역할)
// 그것을 UiState에 업데이트함.
data class WeatherUiState(
    val location: String? = null,
    val mainWeatherImageUrl: String? = null,
    val weatherUiData: List<WeatherVO>? = null,
    val isLoading: Boolean = false,
    val loadWeatherErrorMessage: String? = null,
    val loadLocationErrorMessage: String? = null,
)
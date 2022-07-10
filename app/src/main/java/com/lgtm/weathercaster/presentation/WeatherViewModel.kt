package com.lgtm.weathercaster.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgtm.weathercaster.data.vo.item.mapToCurrentWeatherSummaryVO
import com.lgtm.weathercaster.data.vo.item.mapToDailyWeatherVO
import com.lgtm.weathercaster.data.vo.item.mapToHourlyWeatherVO
import com.lgtm.weathercaster.domain.WeatherRepository
import com.lgtm.weathercaster.utils.LocationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationProvider: LocationProvider,
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: WeatherUiEvent) {
        when (event) {
            WeatherUiEvent.Refresh -> {
                getCurrentWeather(fetchFromRemote = true)
            }
        }
    }

    fun getCurrentWeather(
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            locationProvider.getCurrentLocation()?.also { location ->
                repository.getCurrentWeather(
                    location.latitude,
                    location.longitude,
                    true,
                ).firstOrNull()?.data?.also { weather ->
                    // 성공케이스
                    // 여기서 서버데이터 처럼 구성해서 값 업데이트해주기
                    _uiState.value = _uiState.value.copy(
                        weatherWidgets = listOf(
                            weather.mapToCurrentWeatherSummaryVO(),
                            weather.mapToCurrentWeatherSummaryVO(),
                            weather.mapToCurrentWeatherSummaryVO(),
                            weather.mapToDailyWeatherVO(),
                            weather.mapToDailyWeatherVO(),
                            weather.mapToDailyWeatherVO(),
                            weather.mapToHourlyWeatherVO(),
                            weather.mapToHourlyWeatherVO(),
                            weather.mapToHourlyWeatherVO(),
                        )
                    )
                } ?: run {
                    // 성공은 했지만 비어있음
                    _uiState.value = _uiState.value.copy(
                        loadWeatherErrorMessage = "1"
                    )
                }
            } ?: run {
                // location 로드 실패
                _uiState.value = _uiState.value.copy(
                    loadWeatherErrorMessage = "1"
                )
                Log.d("Doran", "!!!")
            }
        }
    }

}
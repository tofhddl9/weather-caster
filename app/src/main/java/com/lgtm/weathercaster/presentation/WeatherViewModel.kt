package com.lgtm.weathercaster.presentation

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgtm.weathercaster.data.vo.WeatherVO
import com.lgtm.weathercaster.data.vo.item.mapToCurrentWeatherSummaryVO
import com.lgtm.weathercaster.data.vo.item.mapToDailyWeatherVO
import com.lgtm.weathercaster.data.vo.item.mapToHourlyWeatherVO
import com.lgtm.weathercaster.domain.WeatherRepository
import com.lgtm.weathercaster.utils.LocationProvider
import com.lgtm.weathercaster.utils.Response
import com.lgtm.weathercaster.utils.time.SystemTimeProvider
import com.lgtm.weathercaster.utils.time.TimeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationProvider: LocationProvider,
    private val timeProvider: TimeProvider = SystemTimeProvider(),
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

    fun getCurrentWeather(fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            locationProvider.getCurrentLocation()?.also { location ->
                updateLocationInfo(location)
                loadWeather(location.latitude, location.longitude, true)
            } ?: run {
                // location 로드 실패
                onLocationLoadFailed()
            }
        }
    }

    private fun onLocationLoadFailed() {
        _uiState.value = _uiState.value.copy(
            loadWeatherErrorMessage = "1"
        )
    }

    private fun updateLocationInfo(location: Location) {
        _uiState.value = _uiState.value.copy(
            location = locationProvider.getAddress(location)
        )
    }

    private suspend fun loadWeather(latitude: Double, longitude: Double, fetchFromRemote: Boolean) {
        viewModelScope.launch {
            repository.getCurrentWeather(latitude, longitude, true).collect { response ->
                when(response) {
                    is Response.Success -> {
                        onLoadWeatherSuccess(response.data)
                    }
                    is Response.Error -> {
                        onLoadWeatherFailed(response.data, response.message)
                    }
                    is Response.Loading -> {
                        onLoadingWeather(response.isLoading)
                    }
                }
            }
        }
    }

    private fun onLoadWeatherSuccess(data: WeatherVO?) {
        data?.also { weather ->
            val currentTime = weather.current?.dt ?: timeProvider.getCurrentTimeMillis()
            val mainWeather = weather.current?.weatherMetaData?.description ?: ""
            val weatherState = WeatherState.getStateFrom(mainWeather, timeProvider.isNight(currentTime))

            _uiState.value = _uiState.value.copy(
                weatherWidgets = listOf(
                    weather.mapToCurrentWeatherSummaryVO(),
                    weather.mapToHourlyWeatherVO(),
                    weather.mapToDailyWeatherVO(),
                    weather.mapToDailyWeatherVO(),
                ),
                weatherState = weatherState
            )
        } ?: run {
            _uiState.value = _uiState.value.copy(
                loadWeatherErrorMessage = "There is no weather data"
            )
        }
    }

    private fun onLoadWeatherFailed(data: WeatherVO?, message: String?) {
        //
    }

    private fun onLoadingWeather(loading: Boolean) {
        _uiState.value = _uiState.value.copy(
            isLoading = loading
        )
    }


}
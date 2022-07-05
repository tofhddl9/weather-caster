package com.lgtm.weathercaster.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        Log.d("Doran", "@@@")
        // getCurrentWeather()
    }

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
        Log.d("Doran", "@")
        viewModelScope.launch {
            locationProvider.getCurrentLocation()?.also { location ->
                repository.getCurrentWeather(
                    location.latitude,
                    location.longitude,
                    true,
                ).firstOrNull()?.data?.also {
                    _uiState.value = _uiState.value.copy(
                        weather = it
                    )
                } ?: run {
                    _uiState.value = _uiState.value.copy(
                        error = "1"
                    )
                }
            } ?: run {
                // location 실패시.
                _uiState.value = _uiState.value.copy(
                    error = "1"
                )
                Log.d("Doran", "!!!")
            }
        }
    }

}
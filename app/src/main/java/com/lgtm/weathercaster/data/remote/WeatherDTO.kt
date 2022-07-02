package com.lgtm.weathercaster.data.remote

data class WeatherDTO(
    val coord: CoordinateInfo? = null,
    val weather: List<WeatherInfo>? = null,
    val base: String? = null,
    val main: MainInfo? = null,
    val visibility: Int = 0,
    val wind: WindInfo? = null,
    val clouds: CloudInfo? = null,
    val dt: Long = 0,
    val sys: WeatherMetaData? = null,
    val timezone: Long = 0,
    val id: Int = 0,
    val name: String? = null,
    val cod: Int = 0,
)

data class CoordinateInfo(
    val lon: Float = 0.0f,
    val lat: Float = 0.0f,
)

data class WeatherInfo(
    val id: Int = 0,
    val main: String? = null,
    val description: String? = null,
    val icon: String? = null,
)

data class MainInfo(
    val temp: Float = 0.0f,
    val feels_like: Float = 0.0f,
    val temp_min: Float = 0.0f,
    val temp_max: Float = 0.0f,
    val pressure: Int = 0,
    val humidity: Int = 0,
)

data class WindInfo(
    val speed: Float = 0.0f,
    val deg: Int = 0,
)

data class CloudInfo(
    val all: Int = 0,
)

data class WeatherMetaData(
    val type: Int = 0,
    val id: Int = 0,
    val message: Float = 0.0f,
    val country: String? = null,
    val sunrise: Long = 0L,
    val sunset: Long = 0L,
)

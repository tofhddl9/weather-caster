package com.lgtm.weathercaster.data.mapper

import com.lgtm.weathercaster.data.vo.WeatherDataVO
import com.lgtm.weathercaster.data.vo.WeatherMetaDataVO
import com.lgtm.weathercaster.data.vo.WeatherVO
import com.lgtm.weathercaster.data.local.WeatherEntity
import com.lgtm.weathercaster.data.remote.DailyWeatherData
import com.lgtm.weathercaster.data.remote.HourlyWeatherData
import com.lgtm.weathercaster.data.remote.WeatherDTO
import com.lgtm.weathercaster.data.remote.WeatherData
import com.lgtm.weathercaster.data.remote.WeatherMainData

fun WeatherEntity.mapToWeatherVO() = WeatherVO(
)

fun WeatherVO.mapToWeatherEntity() = WeatherEntity(
    foo = 0
)

fun WeatherDTO.mapToWeatherVO() = WeatherVO(
    current = current?.mapToWeatherDataVO(),
    dailyWeathers = daily?.mapToDailyWeatherDataVO(),
    hourlyWeathers = hourly?.mapToHourlyWeatherDataVO(),
)

private fun WeatherData.mapToWeatherDataVO() = WeatherDataVO(
    dt = dt,
    temperature = temp,
    precipitation = rain?.hourly ?: 0f,
    uvi = uvi,
    weatherMetaData = weather?.firstOrNull()?.mapToWeatherMetaDataVO(),
)

private fun WeatherMainData.mapToWeatherMetaDataVO() = WeatherMetaDataVO(
    id = id,
    description = "$main($description)",
    icon = "https://openweathermap.org/img/wn/$icon@2x.png"
)

private fun List<DailyWeatherData>.mapToDailyWeatherDataVO() = this.map {
    WeatherDataVO(
        dt = it.dt,
        temperature = it.temp?.day ?: 0f,
        temperatureMin = it.temp?.min ?: 0f,
        temperatureMax = it.temp?.max ?: 0f,
        precipitation = it.rain ?: 0f,
        uvi = it.uvi,
        weatherMetaData = it.weather?.firstOrNull()?.mapToWeatherMetaDataVO(),
    )
}

private fun List<HourlyWeatherData>.mapToHourlyWeatherDataVO() = this.map {
    WeatherDataVO(
        dt = it.dt,
        temperature = it.temp,
        precipitation = it.rain?.hourly ?: 0f,
        uvi = it.uvi,
        weatherMetaData = it.weather?.firstOrNull()?.mapToWeatherMetaDataVO(),
    )
}

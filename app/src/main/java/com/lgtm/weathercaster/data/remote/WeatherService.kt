package com.lgtm.weathercaster.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("appid") appId: String,
    ): WeatherDTO?

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }
}

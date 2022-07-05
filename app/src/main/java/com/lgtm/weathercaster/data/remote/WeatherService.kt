package com.lgtm.weathercaster.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appId: String,
    ): WeatherDTO?

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }
}

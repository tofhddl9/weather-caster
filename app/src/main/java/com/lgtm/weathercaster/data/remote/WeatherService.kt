package com.lgtm.weathercaster.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") q: String,
        @Query("appid") appId: String,
        @Query("lang") lang: String,
    ): WeatherDTO

}

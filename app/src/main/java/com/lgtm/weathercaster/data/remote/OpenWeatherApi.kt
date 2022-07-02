package com.lgtm.weathercaster.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("weather")
    fun getWeather(
        @Query("q") q: String,
        @Query("appid") appId: String,
        @Query("lang") lang: String,
    ): Call<WeatherInfo>

}

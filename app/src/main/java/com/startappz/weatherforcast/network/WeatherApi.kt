package com.startappz.weatherforcast.network

import com.startappz.weatherforcast.model.Weather
import com.startappz.weatherforcast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET(value = "data/2.5/forecast/daily")
    //q=lisbon&appid
    suspend fun getWeather(
        @Query(value = "q") query: String,
        @Query(value = "appid") appID: String = Constants.API_KEY,
        @Query(value = "units") units: String = "imperial"
    ): Weather
}
package com.startappz.weatherforcast.repository

import com.startappz.weatherforcast.data.DataOrException
import com.startappz.weatherforcast.model.Weather
import com.startappz.weatherforcast.model.WeatherObject
import com.startappz.weatherforcast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(cityQuery: String): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch (ex: Exception) {
            return DataOrException(e = ex)
        }

        return DataOrException(response)
    }
}
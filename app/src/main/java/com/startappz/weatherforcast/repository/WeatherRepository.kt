package com.startappz.weatherforcast.repository

import com.startappz.weatherforcast.data.DataOrException
import com.startappz.weatherforcast.model.Weather
import com.startappz.weatherforcast.model.WeatherObject
import com.startappz.weatherforcast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(cityQuery: String, unit: String): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery, units = unit)
        } catch (ex: Exception) {
            ex.printStackTrace()
            return DataOrException(e = ex)
        }

        return DataOrException(response)
    }
}
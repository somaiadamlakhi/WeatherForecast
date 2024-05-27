package com.startappz.weatherforcast.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startappz.weatherforcast.data.DataOrException
import com.startappz.weatherforcast.model.Weather
import com.startappz.weatherforcast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    init {
        loadWeather()
    }

     fun loadWeather() {
        viewModelScope.launch {
            getWeatherData(city = "lisbon")
        }

    }

     suspend fun getWeatherData(city: String)
            : DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city)

    }


}
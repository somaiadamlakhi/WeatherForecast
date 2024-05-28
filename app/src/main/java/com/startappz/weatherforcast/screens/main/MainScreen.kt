package com.startappz.weatherforcast.screens.main

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.startappz.weatherforcast.data.DataOrException
import com.startappz.weatherforcast.model.Weather
import com.startappz.weatherforcast.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavHostController, viewmodel: MainViewModel = hiltViewModel()) {
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>
            >(initialValue = DataOrException(loading = true)) {
        value = viewmodel.getWeatherData("Jordan")

    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else {
        weatherData.data?.let { MainScaffold(it, navController) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScaffold(weather: Weather? = null, navController: NavController? = null) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "${weather?.city?.name}, ${weather?.city?.country}",
                navController = navController,
                elevation = 5.dp
            )
        }
    ) {
        it.toString()
        if (weather != null) {
            MainContent(weather)
        }
    }

}

@Composable
fun MainContent(weather: Weather) {
    Text(text = weather.city.name)
}

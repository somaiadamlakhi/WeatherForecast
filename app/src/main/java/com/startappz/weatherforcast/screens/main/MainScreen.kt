package com.startappz.weatherforcast.screens.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.startappz.weatherforcast.data.DataOrException
import com.startappz.weatherforcast.model.Weather
import com.startappz.weatherforcast.model.WeatherItem
import com.startappz.weatherforcast.navigation.WeatherScreens
import com.startappz.weatherforcast.utils.Constants.IMAGE_PATH
import com.startappz.weatherforcast.utils.fehToCel
import com.startappz.weatherforcast.utils.formatDate
import com.startappz.weatherforcast.widgets.HumidityWindPressureRow
import com.startappz.weatherforcast.widgets.SunsetSunRiseRow
import com.startappz.weatherforcast.widgets.WeatherAppBar
import com.startappz.weatherforcast.widgets.WeatherDetailRow

@Composable
fun MainScreen(
    navController: NavHostController,
    viewmodel: MainViewModel = hiltViewModel(),
    city: String?="Jordan"
) {

    Log.d("TAG", "MainScreen: $city")
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>
            >(initialValue = DataOrException(loading = true)) {
//        var defaultCity = city
//        if (defaultCity.isNullOrEmpty()) defaultCity = "Jordan"
        value = viewmodel.getWeatherData("Jordan")


    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else {
        weatherData.data?.let { MainScaffold(it, navController) }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(weather: Weather? = null, navController: NavController? = null) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "${weather?.city?.name}, ${weather?.city?.country}",
                navController = navController,
                onAddActionClicked = {
                    navController?.navigate(WeatherScreens.SearchScreen.name)
                }
            )
        }
    ) {
        it.toString()
        if (weather != null) {
            MainContent(weather)
        }
    }

}

@Preview
@Composable
fun MainContent(weather: Weather? = null) {
    val weatherItem = weather?.list?.get(0)
    val imageUrl = "$IMAGE_PATH${weatherItem?.weather?.get(0)?.icon}.png"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatDate(weatherItem?.dt),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(6.dp)
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                WeatherStateImage(imageUrl = imageUrl)
                Text(
                    text = (weatherItem?.temp?.day)?.fehToCel() + "º",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = weatherItem?.weather?.get(0)?.main ?: "",
                    style = MaterialTheme.typography.titleSmall,
                    fontStyle = FontStyle.Italic
                )
            }
        }

        HumidityWindPressureRow(weatherItem, true)
        HorizontalDivider()
        SunsetSunRiseRow(weather = weather?.list?.get(0))
        Text(
            "This Week",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ) {

                weather?.list?.let {
                    items(items = it) { item: WeatherItem ->
                        WeatherDetailRow(weather = item)

                    }
                }


            }

        }
    }

}


@Composable
fun WeatherStateImage(imageUrl: String) {

    AsyncImage(
        model = imageUrl,
        contentDescription = "Image Description",
        modifier = Modifier.size(80.dp),
    )
}

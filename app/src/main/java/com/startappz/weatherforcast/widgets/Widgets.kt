package com.startappz.weatherforcast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.startappz.weatherforcast.R
import com.startappz.weatherforcast.model.WeatherItem
import com.startappz.weatherforcast.screens.main.WeatherStateImage
import com.startappz.weatherforcast.utils.Constants.IMAGE_PATH
import com.startappz.weatherforcast.utils.formatDate
import com.startappz.weatherforcast.utils.formatDateTime
import com.startappz.weatherforcast.utils.formatDecimals

@Composable
fun HumidityWindPressureRow(weatherItem: WeatherItem?, isImperial: Boolean) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier.padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.humidity), contentDescription = "humidity",
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = "${weatherItem?.clouds}%",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Row(
            modifier = Modifier.padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.pressure), contentDescription = "humidity",
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = "${weatherItem?.pressure} mph",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Row() {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${(weatherItem?.speed?.formatDecimals())} " + if (isImperial) "mph" else "m/s",
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }

}


@Composable
fun SunsetSunRiseRow(weather: WeatherItem?) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 15.dp, bottom = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Row {
            Image(painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "sunrise",
                modifier = Modifier.size(30.dp))
            Text(text = formatDateTime(weather?.sunrise?:0),
                style = MaterialTheme.typography.bodyMedium)

        }

        Row {

            Text(text = formatDateTime(weather?.sunset?:0),
                style = MaterialTheme.typography.bodyMedium)
            Image(painter = painterResource(id = R.drawable.sunset),
                contentDescription = "sunset",
                modifier = Modifier.size(30.dp))


        }

    }


}


@Composable
fun WeatherDetailRow(weather: WeatherItem) {
    val imageUrl = "$IMAGE_PATH${weather.weather[0].icon}.png"
    Surface(
        Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                formatDate(weather.dt)
                    .split(",")[0],
                modifier = Modifier.padding(start = 5.dp)
            )
            WeatherStateImage(imageUrl = imageUrl)
            Surface(modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)
            ) {
                Text(weather.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.titleMedium)

            }
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color.Blue.copy(alpha = 0.7f),
                    fontWeight = FontWeight.SemiBold
                )
                ){
                    append((weather.temp.max).formatDecimals() + "º")

                }
                withStyle(
                    style = SpanStyle(
                        color = Color.LightGray)
                ){
                    append((weather.temp.min).formatDecimals() + "º")
                }
            })

        }


    }










}

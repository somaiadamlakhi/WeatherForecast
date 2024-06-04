package com.startappz.weatherforcast.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.startappz.weatherforcast.model.Favorite
import com.startappz.weatherforcast.navigation.WeatherScreens
import com.startappz.weatherforcast.screens.favourites.FavoriteViewModel
import com.startappz.weatherforcast.ui.theme.LightGreen
import com.startappz.weatherforcast.ui.theme.OffWhite

@Composable
fun CityRow(
    navController: NavController,
    favorite: Favorite,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                navController.navigate(WeatherScreens.MainScreen.name + "/${favorite.city}")
            },
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = LightGreen
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {

            Text(text = favorite.city, modifier = Modifier.padding(start = 4.dp))

            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = OffWhite
            ) {
                Text(
                    text = favorite.country,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodySmall
                )

            }
            Icon(
                imageVector = Icons.Rounded.Delete, contentDescription = "delete",
                modifier = Modifier.clickable {
                    favoriteViewModel.deleteFavorite(favorite)

                },
                tint = Color.Red.copy(alpha = 0.3f)
            )

        }

    }

}
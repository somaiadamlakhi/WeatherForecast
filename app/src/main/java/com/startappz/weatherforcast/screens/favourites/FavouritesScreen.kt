package com.startappz.weatherforcast.screens.favourites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.startappz.weatherforcast.R
import com.startappz.weatherforcast.model.WeatherItem
import com.startappz.weatherforcast.widgets.CityRow
import com.startappz.weatherforcast.widgets.WeatherAppBar
import com.startappz.weatherforcast.widgets.WeatherDetailRow
import kotlinx.coroutines.flow.forEach

@Composable
fun FavouritesScreen(
    navController: NavHostController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            WeatherAppBar(
                title = stringResource(id = R.string.favorite_cities),
                navController = navController,
                icon = Icons.AutoMirrored.Default.ArrowBack,
                isMainScreen = false,
                onButtonClick = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {

            val list = favoriteViewModel.favList.collectAsState().value

            LazyColumn(
                modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ) {

                itemsIndexed(
                    items = list,
                    key = { _, item -> item.city },
                ) { _, city ->
                    CityRow(navController = navController, favorite = city)
                }


            }
        }
    }

}
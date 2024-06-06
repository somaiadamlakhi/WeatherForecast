package com.startappz.weatherforcast.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.startappz.weatherforcast.R
import com.startappz.weatherforcast.navigation.WeatherScreens
import com.startappz.weatherforcast.widgets.SearchBar
import com.startappz.weatherforcast.widgets.WeatherAppBar

@Composable
fun SearchScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            WeatherAppBar(
                title = stringResource(id = R.string.search_title),
                icon = Icons.AutoMirrored.Default.ArrowBack,
                isMainScreen = false,
                navController = navController,
                onButtonClick = {
                    navController.popBackStack()
                }
            )
        }
    ) {

        Surface {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) { mCity ->
                    navController.navigate(WeatherScreens.MainScreen.name + "/$mCity")
                    Log.d("SEARCH SCREEN", "SearchScreen: $it")
                }
            }
        }

    }

}





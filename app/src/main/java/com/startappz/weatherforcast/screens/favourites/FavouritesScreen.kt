package com.startappz.weatherforcast.screens.favourites

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun FavouritesScreen(navController: NavHostController, favoriteViewModel: FavoriteViewModel = hiltViewModel()) {
    Text(text = "Favourite Screen")

}
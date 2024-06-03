package com.startappz.weatherforcast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.startappz.weatherforcast.screens.main.MainScreen
import com.startappz.weatherforcast.screens.splash.WeatherSplashScreen
import com.startappz.weatherforcast.screens.main.MainViewModel
import com.startappz.weatherforcast.screens.search.SearchScreen
import com.startappz.weatherforcast.screens.search.SearchViewModel

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {
        /**
         * Splash Screen
         */
        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController)
        }

        /**
         * Main Screen
         */
        composable(WeatherScreens.MainScreen.name) {
            val viewmodel = hiltViewModel<MainViewModel>()
            MainScreen(navController, viewmodel)
        }

        /**
         * Search Screen
         */
        composable(WeatherScreens.SearchScreen.name) {
            val viewmodel = hiltViewModel<SearchViewModel>()
            SearchScreen(navController, viewmodel)
        }
    }
}
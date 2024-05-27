package com.startappz.weatherforcast.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.startappz.weatherforcast.screens.main.MainScreen
import com.startappz.weatherforcast.screens.WeatherSplashScreen

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
            MainScreen(navController)
        }
    }
}
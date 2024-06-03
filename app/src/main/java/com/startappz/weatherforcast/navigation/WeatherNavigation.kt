package com.startappz.weatherforcast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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

        val route = WeatherScreens.MainScreen.name
        /**
         * Main Screen
         */
        composable(
            "$route/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                })
        ) { navBack ->
            navBack.arguments?.getString("city").let { city ->

                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(
                    navController = navController, mainViewModel,
                    city = city
                )
            }


        }

        composable(
            "$route/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                })
        ) { navBack ->
            navBack.arguments?.getString(Routes.CITY.key).let { city ->

                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(
                    navController = navController, mainViewModel,
                    city = city
                )
            }


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
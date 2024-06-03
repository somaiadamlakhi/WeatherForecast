package com.startappz.weatherforcast.navigation

enum class WeatherScreens {
    SplashScreen,
    MainScreen,
    AboutScreen,
    FavouriteScreen,
    SearchScreen,
    SettingsScreen
}

enum class Routes(val key: String) {
    CITY("city")

}

enum class DropDownMenuValues(val index: Int) {
    ABOUT(0),
    FAVOURITES(1),
    SETTINGS(2)

}
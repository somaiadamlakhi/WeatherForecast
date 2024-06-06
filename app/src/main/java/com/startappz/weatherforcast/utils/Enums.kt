package com.startappz.weatherforcast.utils

enum class Routes(val key: String) {
    CITY("city")

}

enum class DropDownMenuValues(val index: Int) {
    ABOUT(0),
    FAVOURITES(1),
    SETTINGS(2)

}

enum class TempUnits(val unit: String) {
    Fahrenheit("Imperial (F)"),
    Celsius("Metric (C)")
}
package com.startappz.weatherforcast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.startappz.weatherforcast.model.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}
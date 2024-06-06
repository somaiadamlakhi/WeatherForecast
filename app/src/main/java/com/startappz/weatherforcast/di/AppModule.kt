package com.startappz.weatherforcast.di

import android.content.Context
import androidx.room.Room
import com.startappz.weatherforcast.data.UnitDao
import com.startappz.weatherforcast.data.WeatherDao
import com.startappz.weatherforcast.data.WeatherDatabase
import com.startappz.weatherforcast.network.WeatherApi
import com.startappz.weatherforcast.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): WeatherDatabase = Room.databaseBuilder(
        context,
        WeatherDatabase::class.java,
        "weather_database"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao = weatherDatabase.weatherDao()

    @Singleton
    @Provides
    fun provideUnitDao(weatherDatabase: WeatherDatabase): UnitDao = weatherDatabase.unitDao()

}
package com.startappz.weatherforcast.repository

import com.startappz.weatherforcast.data.WeatherDao
import com.startappz.weatherforcast.model.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDBRepository @Inject constructor(
    private val weatherDao: WeatherDao
) {

    fun getFavorite(): Flow<List<Favorite>> = weatherDao.getFavorites()


    suspend fun insertFavorite(favorite: Favorite) = weatherDao.insertFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavorite(favorite)
    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()
    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)
    suspend fun getFavById(city: String): Favorite = weatherDao.getFavById(city)
}
package com.startappz.weatherforcast.screens.favourites

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startappz.weatherforcast.model.Favorite
import com.startappz.weatherforcast.repository.WeatherDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherDBRepository) : ViewModel() {
    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorite().distinctUntilChanged().collect { listOfFaves ->
                    if (listOfFaves.isEmpty()) {
                        Log.d("TAG", ": Empty favs ")
                    } else {
                        _favList.value = listOfFaves
                        Log.d("FAVS", ":${favList.value} ")
                    }
                }

        }
    }

    fun insertFavorite(favorite: Favorite) = viewModelScope.launch { repository.insertFavorite(favorite) }
    fun updateFavorite(favorite: Favorite) = viewModelScope.launch { repository.updateFavorite(favorite) }
    fun deleteFavorite(favorite: Favorite) = viewModelScope.launch { repository.deleteFavorite(favorite) }
}
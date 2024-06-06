package com.startappz.weatherforcast.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startappz.weatherforcast.model.Unit
import com.startappz.weatherforcast.repository.UnitDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    repository: UnitDBRepository
) : ViewModel() {

    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    private val unitList = _unitList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnits().distinctUntilChanged().collect { listOfUnits ->
                if (listOfUnits.isNullOrEmpty()) {
                    Log.d("Units TAG", "Empty Units: ")
                } else {
                    Log.d("Units TAG", "$listOfUnits")
                }

            }
        }
    }
}
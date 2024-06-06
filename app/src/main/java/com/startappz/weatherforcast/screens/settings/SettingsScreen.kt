package com.startappz.weatherforcast.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.startappz.weatherforcast.R
import com.startappz.weatherforcast.utils.TempUnits
import com.startappz.weatherforcast.widgets.WeatherAppBar

@Composable
fun SettingsScreen(
    navController: NavHostController,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {

    var unitToggleState by remember { mutableStateOf(false) }
    val measurements = listOf(TempUnits.Fahrenheit.unit, TempUnits.Celsius.unit)

    val choiceState by remember {
        mutableStateOf("")
    }
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Settings",
            icon = Icons.AutoMirrored.Default.ArrowBack,
            false,
            navController = navController
        ) {
            navController.popBackStack()
        }
    }) {
        Surface(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.change_unit_of_measurement),
                    modifier = Modifier.padding(15.dp)
                )


                IconToggleButton(checked = !unitToggleState, onCheckedChange = {
                    unitToggleState = !it
                    if (unitToggleState) {
//                        choiceState =
                    }
                }) {


                }
            }

        }

    }


}
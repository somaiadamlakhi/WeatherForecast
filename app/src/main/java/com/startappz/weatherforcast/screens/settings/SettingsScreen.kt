package com.startappz.weatherforcast.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.startappz.weatherforcast.R
import com.startappz.weatherforcast.model.Unit
import com.startappz.weatherforcast.ui.theme.Orange
import com.startappz.weatherforcast.utils.TempUnits
import com.startappz.weatherforcast.widgets.WeatherAppBar

@Composable
fun SettingsScreen(
    navController: NavHostController, settingsViewModel: SettingsViewModel = hiltViewModel()
) {

    var unitToggleState by remember { mutableStateOf(false) }
    val measurementUnits = listOf(TempUnits.Fahrenheit.unit, TempUnits.Celsius.unit)


    val choiceFromDb = settingsViewModel.unitList.collectAsState().value

    val defaultChoice = if (choiceFromDb.isEmpty()) measurementUnits[0]
    else choiceFromDb[0].unit


    var choiceState by remember {
        mutableStateOf(defaultChoice)
    }

    Scaffold(topBar = {
        WeatherAppBar(
            title = "Settings", icon = Icons.AutoMirrored.Default.ArrowBack, false, navController = navController
        ) {
            navController.popBackStack()
        }
    }) {

        unitToggleState = if (choiceFromDb.any { it.unit == TempUnits.Celsius.unit }) false else true

        Surface(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            Column(
                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.change_unit_of_measurement), modifier = Modifier.padding(15.dp)
                )


                IconToggleButton(
                    checked = !unitToggleState,
                    onCheckedChange = { checked ->
                        unitToggleState = !checked
                        choiceState = if (unitToggleState) {
                            TempUnits.Fahrenheit.unit
                        } else {
                            TempUnits.Celsius.unit
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clip(shape = RectangleShape)
                        .padding(5.dp)
                        .background(Color.Magenta.copy(0.4f)),
                ) {
                    Text(
                        text = if (unitToggleState) TempUnits.Fahrenheit.unit
                        else TempUnits.Celsius.unit
                    )

                }

                Button(
                    onClick = {
                        settingsViewModel.deleteAllUnits()
                        settingsViewModel.insertUnit(Unit(unit = choiceState))
                        navController.popBackStack()
                    }, modifier = Modifier.clip(RoundedCornerShape(10.dp)), colors = ButtonDefaults.buttonColors(
                        containerColor = Orange
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        color = Color.White,
                        fontSize = 17.sp,
                        text = stringResource(id = R.string.btn_save)
                    )
                }
            }

        }
    }

}
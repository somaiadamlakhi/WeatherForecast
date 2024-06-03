package com.startappz.weatherforcast.screens.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.startappz.weatherforcast.widgets.WeatherAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, viewmodel: SearchViewModel = hiltViewModel()) {


    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            WeatherAppBar(
                title = "Search",
                navController = navController,
                icon = Icons.AutoMirrored.Default.ArrowBack,
                isMainScreen = false,
                onButtonClick = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        it.toString()
        Surface {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 60.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Log.d("SEARCH SCREEN", "SearchScreen: $it")
                }
            }
        }

    }

}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyBoardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }
    Column{
        CommonTextField(
            modifier = modifier.fillMaxWidth(),
            valueState = searchQueryState,
            placeHolder = "City Name",
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                else {
                    onSearch(searchQueryState.value.trim())
                    searchQueryState.value = ""
                    keyBoardController?.hide()
                }
            },
        )
    }
}

@Composable
fun CommonTextField(
    modifier: Modifier=Modifier,
    valueState: MutableState<String>,
    placeHolder: String,
    keyBoardType: KeyboardType = KeyboardType.Text,
    imeActions: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(
        modifier = modifier,
        value = valueState.value,
        maxLines = 1,
        onValueChange = {
            valueState.value = it
        },
        label = {
            Text(text = placeHolder)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyBoardType,
            imeAction = imeActions,
            capitalization = KeyboardCapitalization.Words
        ),
        keyboardActions = onAction,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Blue,
            cursorColor = Color.Black
        )


    )
}

package com.startappz.weatherforcast.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

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
    Column {
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
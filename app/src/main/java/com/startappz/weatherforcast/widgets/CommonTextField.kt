package com.startappz.weatherforcast.widgets

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
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
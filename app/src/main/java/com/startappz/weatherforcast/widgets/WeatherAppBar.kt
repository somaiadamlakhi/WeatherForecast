package com.startappz.weatherforcast.widgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.startappz.weatherforcast.navigation.DropDownMenuValues
import com.startappz.weatherforcast.navigation.WeatherScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String? = "title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 5.dp,
    navController: NavController? = null,
    onAddActionClicked: () -> Unit = {},
    onButtonClick: () -> Unit = {},
) {
    val showDialog = remember {
        mutableStateOf(false)
    }

    if (showDialog.value) ShowSettingsDropDownMenu(showDialog = showDialog, navController = navController)

    Surface(shadowElevation = elevation) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = title ?: "",
                    color = MaterialTheme.colorScheme.primary,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                )
            },
            actions = {
                if (isMainScreen) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search",
                        modifier = Modifier.clickable {
                            onAddActionClicked()
                        }
                    )

                    Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                    IconButton(onClick = {
                        showDialog.value = true
                    }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "more"
                        )
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                }


            },
            navigationIcon = {

                if (icon != null) {
                    Icon(imageVector = icon, contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            onButtonClick.invoke()
                        })
                }

            }
        )
    }


}

@Composable
fun ShowSettingsDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController?) {
    var expanded by remember {
        mutableStateOf(true)
    }
    val items = listOf(
        "About",
        "Favourites",
        "Settings"
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(
                Alignment.TopEnd
            )
            .absolutePadding(top = 45.dp, right = 20.dp),

        ) {

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }, modifier =
            Modifier
                .width(140.dp)
                .background(Color.White)
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(text = {
                    Row {
                        Icon(
                            imageVector = when (index) {
                                DropDownMenuValues.ABOUT.index -> Icons.Default.Info
                                DropDownMenuValues.FAVOURITES.index -> Icons.Default.Favorite
                                DropDownMenuValues.SETTINGS.index -> Icons.Default.Settings
                                else -> Icons.Default.Info

                            }, contentDescription = item
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = item)
                    }

                }, onClick = {
                    expanded = false
                    showDialog.value = false
                    when (index) {
                        DropDownMenuValues.ABOUT.index ->  navController?.navigate(WeatherScreens.AboutScreen.name)
                        DropDownMenuValues.FAVOURITES.index ->  navController?.navigate(WeatherScreens.FavouriteScreen.name)
                        DropDownMenuValues.SETTINGS.index ->  navController?.navigate(WeatherScreens.SettingsScreen.name)
                        else -> Icons.Default.Info

                    }

                    Log.d("TAG", "ShowSettingsDropDownMenu: $item")
                })

            }
        }
    }
}

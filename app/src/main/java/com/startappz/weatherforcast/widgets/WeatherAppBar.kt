package com.startappz.weatherforcast.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
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
    scrollBehavior: TopAppBarScrollBehavior? = null,

    ) {

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
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "more",
                        modifier = Modifier.clickable {
                            onButtonClick()
                        }
                    )
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
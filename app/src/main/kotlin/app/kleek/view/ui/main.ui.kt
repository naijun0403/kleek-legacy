package app.kleek.view.ui

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.app.ComponentActivity
import app.kleek.R

@Composable
fun MainScreen(activity: ComponentActivity) {
    KleekNavigationBar(activity)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KleekNavigationBar(activity: ComponentActivity) {
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val list = listOf(
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "home") },
                        label = { Text(text = activity.getString(R.string.home)) },
                        selected = selectedItem == 0,
                        onClick = { selectedItem = 0 }
                    ),
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Settings, contentDescription = "settings") },
                        label = { Text(text = activity.getString(R.string.settings)) },
                        selected = selectedItem == 1,
                        onClick = { selectedItem = 1 }
                    ),
                )
            }
        }
    ) {

    }
}
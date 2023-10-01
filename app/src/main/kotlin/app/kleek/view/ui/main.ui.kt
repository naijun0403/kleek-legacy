package app.kleek.view.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ComponentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.kleek.R
import app.kleek.core.constant.Constant
import app.kleek.viewmodel.MainViewModel
import app.kleek.viewmodel.SettingModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    KleekNavigationBar(viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KleekNavigationBar(viewModel: MainViewModel) {
    var selectedItem by remember { mutableStateOf(0) }
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "home") },
                    label = { Text(stringResource(id = R.string.home)) },
                    selected = selectedItem == 0,
                    onClick = {
                        selectedItem = 0
                        navController.navigate("home") {
                            navController.graph.startDestinationRoute?.let {
                                popUpTo(it) { saveState = true }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "settings") },
                    label = { Text(stringResource(id = R.string.settings)) },
                    selected = selectedItem == 1,
                    onClick = {
                        selectedItem = 1
                        navController.navigate("settings") {
                            navController.graph.startDestinationRoute?.let {
                                popUpTo(it) { saveState = true }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        },

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            NavGraphView(navController = navController, viewModel = viewModel)
        }
    }
}

@Composable
fun NavGraphView(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }

        composable("settings") {
            SettingsScreen(viewModel)
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(30.dp))

        Text(text = "Kleek", fontSize = 30.sp)

        Spacer(modifier = Modifier.padding(30.dp))

        if (SettingModel.load().powerOn) {
            BotEnabledCard()
        } else {
            BotDisabledCard()
        }
    }
}

@Composable
fun BotEnabledCard() {
    Card(
        modifier = Modifier
            .size(
                width = 350.dp,
                height = 110.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "check",
                    modifier = Modifier.size(25.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                )

                Spacer(modifier = Modifier.padding(20.dp))

                Column {
                    Text(text = "활성화 됨", fontSize = 18.sp)

                    Spacer(modifier = Modifier.padding(2.dp))

                    Text(text = "현재 버전 정보: ${Constant.version}")
                }
            }
        }
    }
}

@Composable
fun BotDisabledCard() {
    Card(
        modifier = Modifier
            .size(
                width = 350.dp,
                height = 110.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    imageVector = Icons.Default.Close,
                    contentDescription = "check",
                    modifier = Modifier.size(25.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                )

                Spacer(modifier = Modifier.padding(20.dp))

                Column {
                    Text(text = "활성화 안됨", fontSize = 18.sp)

                    Spacer(modifier = Modifier.padding(2.dp))

                    Text(text = "현재 버전 정보: ${Constant.version}")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: MainViewModel) {
    var dialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogValue by remember { mutableStateOf("") }
    var dialogOnClick: (String) -> Unit by remember { mutableStateOf({}) }

    var alertDialogEnabled by remember { mutableStateOf(false) }
    var alertDialogContent by remember { mutableStateOf("") }

    var botPowerOn by remember { mutableStateOf(SettingModel.load().powerOn) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SettingsElement(title = "봇 설정") {
            SwitchButton(
                title = "봇 활성화",
                checked = botPowerOn,
                onCheckedChange = {
                    botPowerOn = it
                    SettingModel.save(SettingModel.load().copy(powerOn = it))
                }
            )

            SettingButton(
                title = "패키지 이름",
                description = "현재 값: ${SettingModel.load().packageName}",
                buttonText = "값 변경",
            ) {
                dialog = true
                dialogTitle = "패키지 이름"
                dialogValue = SettingModel.load().packageName
                dialogOnClick = {
                    SettingModel.save(SettingModel.load().copy(packageName = it))
                }
            }

            SettingButton(
                title = "버전 설정 초기화",
                description = "메소드 버전 설정을 초기화 합니다.",
                buttonText = "초기화"
            ) {
                viewModel.resetVersionConfig()
                alertDialogContent = "버전 설정 초기화가 완료되었습니다."
                alertDialogEnabled = true
            }
        }
    }
    
    if (dialog) {
        AlertDialog(
            onDismissRequest = { dialog = false },
            title = { Text(text = dialogTitle) },
            text = {
                TextField(
                    value = dialogValue,
                    onValueChange = {
                        dialogValue = it
                    },
                    shape = RoundedCornerShape(18.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = MaterialTheme.colorScheme.onSurface,
                        errorIndicatorColor = MaterialTheme.colorScheme.error,
                    ),
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        dialogOnClick(dialogValue)
                        dialog = false
                    },
                ) {
                    Text(text = "확인")
                }
            },
        )
    }

    if (alertDialogEnabled) {
        AlertDialog(
            onDismissRequest = { alertDialogEnabled = false },
            title = {
                Text(stringResource(id = R.string.alert_title))
            },
            text = {
                Text(text = alertDialogContent)
            },
            confirmButton = {
                Button(
                    onClick = { alertDialogEnabled = false }
                ) {
                    Text(stringResource(id = R.string.confirm_button))
                }
            },
        )
    }
}

@Composable
fun SettingsElement(title: String, content: @Composable () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = title, fontSize = 30.sp)
        }

        content()
    }
}

@Composable
fun SwitchButton(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = title, fontSize = 18.sp)
        }

        Row(
            modifier = Modifier
                .then(Modifier.weight(1f))
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
        }
    }
}

@Composable
fun SettingButton(
    title: String,
    description: String,
    buttonText: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column {
                Text(text = title, fontSize = 15.sp)

                Spacer(modifier = Modifier.padding(2.dp))

                Text(text = description, fontSize = 12.sp)
            }
        }

        Row(
            modifier = Modifier
                .then(Modifier.weight(1f))
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = onClick) {
                Text(text = buttonText)
            }
        }
    }
}
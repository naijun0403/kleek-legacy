package app.kleek.view.ui

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.kleek.R
import app.kleek.reflow.root.RootService
import app.kleek.view.activity.MainActivity
import app.kleek.viewmodel.InitialSetupViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun SetupScreen(viewModel: InitialSetupViewModel, activity: ComponentActivity) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "root") {
        composable("root") {
            BaseUI {
                RootSetupScreen(navController, activity)
            }
        }

        composable("ready") {
            BaseUI {
                ReadySetupScreen(viewModel, activity)
            }
        }
    }
}

@Composable
fun RootSetupScreen(
    navController: NavController,
    activity: ComponentActivity
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 100.dp)
    ) {
        var alertDialogEnabled by remember { mutableStateOf(false) }
        var content by remember { mutableStateOf("") }
        var nextEnabled by remember { mutableStateOf(false) }

        if (alertDialogEnabled) {
            AlertDialog(
                onDismissRequest = { alertDialogEnabled = false },
                title = {
                    Text(stringResource(id = R.string.alert_title))
                },
                text = {
                    Text(text = content)
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

        Column(
            modifier = Modifier.padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                stringResource(id = R.string.need_root_permission),
                style = MaterialTheme.typography.titleLarge
            )

            Button(
                onClick = {
                    if (RootService.allowRootPermission()) {
                        alertDialogEnabled = true
                        content = activity.getString(R.string.allow_root)
                        nextEnabled = true
                    } else {
                        alertDialogEnabled = true
                        content = activity.getString(R.string.disallow_root)
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(stringResource(id = R.string.request_permission))
            }
        }

        Spacer(modifier = Modifier.padding(top = 50.dp))

        Button(
            onClick = {
                if (nextEnabled) {
                    navController.navigate("ready")
                }
            },
            modifier = Modifier
                .padding(top = 150.dp)
                .height(50.dp)
                .width(100.dp),
            enabled = nextEnabled
        ) {
            Text(stringResource(id = R.string.next_button))
        }
    }
}

@Composable
fun ReadySetupScreen(
    viewModel: InitialSetupViewModel,
    activity: ComponentActivity
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 100.dp)
    ) {
        var alertDialogEnabled by remember { mutableStateOf(false) }
        var content by remember { mutableStateOf("") }
        var nextEnabled by remember { mutableStateOf(false) }

        if (alertDialogEnabled) {
            AlertDialog(
                onDismissRequest = { alertDialogEnabled = false },
                title = {
                    Text(stringResource(id = R.string.alert_title))
                },
                text = {
                    Text(text = content)
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

        Column(
            modifier = Modifier.padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // title
            Text(
                stringResource(id = R.string.create_configuration_title),
                style = MaterialTheme.typography.titleLarge
            )

            // description
            Text(
                stringResource(id = R.string.create_configuration_content),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )

            Button(
                onClick = {
                    runBlocking {
                        if (viewModel.createDefaultConfig()) {
                            alertDialogEnabled = true
                            content = activity.getString(R.string.complete_create_configuration)
                            nextEnabled = true
                        } else {
                            alertDialogEnabled = true
                            content = activity.getString(R.string.failed_create_configuration)
                        }
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(stringResource(id = R.string.create_configuration_button))
            }
        }

        Spacer(modifier = Modifier.padding(top = 50.dp))

        Button(
            onClick = {
                if (nextEnabled) {
                    val sharedPreferences = activity.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
                    sharedPreferences.edit().putBoolean("isFirstRun", false).apply()
                    Intent(activity, MainActivity::class.java).apply {
                        activity.startActivity(this)
                        activity.finish()
                    }
                }
            },
            modifier = Modifier
                .padding(top = 150.dp)
                .height(50.dp)
                .width(100.dp),
            enabled = nextEnabled
        ) {
            Text(stringResource(id = R.string.complete_button))
        }
    }
}
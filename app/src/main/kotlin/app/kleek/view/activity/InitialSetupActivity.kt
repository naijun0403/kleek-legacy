package app.kleek.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import app.kleek.view.ui.SetupScreen
import app.kleek.view.ui.theme.KleekTheme
import app.kleek.viewmodel.InitialSetupViewModel

class InitialSetupActivity : ComponentActivity() {

    private val viewModel: InitialSetupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KleekTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupScreen(viewModel, this@InitialSetupActivity)
                }
            }
        }
    }

}
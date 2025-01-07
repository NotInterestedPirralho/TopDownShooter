package com.example.topdownshooter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.topdownshooter.login.LoginView
import com.example.topdownshooter.ui.theme.TopDownShooterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TopDownShooterTheme {
                val navController = rememberNavController()

                NavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    startDestination = Screen.Login.route
                ) {
                    composable(Screen.Login.route) {
                        LoginView(
                            modifier = Modifier.fillMaxSize(),
                            onLoginSuccess = {
                                navController.navigate(Screen.Home.route)
                            }
                        )
                    }

                    composable(Screen.Home.route) {
                        Home(
                            modifier = Modifier.fillMaxSize(),
                            onPlayClick = {
                                navController.navigate(Screen.Game.route);
                            }
                        )
                    }
                }
            }
        }
    }
}

sealed class Screen (val route:String){
    object Login : Screen("login")
    object Home : Screen("home")

}
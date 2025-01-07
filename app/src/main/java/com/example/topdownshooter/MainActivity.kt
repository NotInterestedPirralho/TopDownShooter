package com.example.topdownshooter

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.topdownshooter.login.LoginView
import com.example.topdownshooter.login.RegisterView
import com.example.topdownshooter.ui.theme.TopDownShooterTheme
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.auth


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        WindowCompat.setDecorFitsSystemWindows(window, false)
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
                            },navController = navController
                        )
                    }
                    composable (Screen.Register.route){
                        RegisterView(navController = navController,onRegisterSuccess ={
                            navController.navigate(Screen.Home.route)
                        })
                    }

                    composable(Screen.Home.route) {
                        Home(
                            modifier = Modifier.fillMaxSize(),
                            navController = navController,
                            onPlayClick =
                            {navController.navigate(Screen.GameScreen.route)},
                            //onHighscoreClick = { navController.navigate("highscore") }
                        )
                    }
                    composable(Screen.HighScore.route) {
                        HighscoreView()
                    }
                    composable(Screen.GameScreen.route) {
                        GameScreenView()
                    }


                }
                LaunchedEffect(Unit) {val auth = Firebase.auth
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        navController.navigate(Screen.Home.route)
                    } else
                        navController.navigate(Screen.Login.route)
                }
            }
        }
    }
}

sealed class Screen (val route:String){
    object Login : Screen("login")
    object Home : Screen("home")
    object Register : Screen("register")
    object HighScore : Screen("highscore")
    object GameScreen : Screen("gamescreen")
}
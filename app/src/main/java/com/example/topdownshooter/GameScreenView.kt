package com.example.topdownshooter

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController

@Composable
fun GameScreenView (
    navController: NavController
    ,onGameOver : () -> Unit = {}
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    val density = configuration.densityDpi / 160f
    val screenWidthPx = screenWidth * density
    val screenHeightPx = screenHeight * density

    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(factory = { context ->
        GameView(context = context,
            width = screenWidthPx.toInt(),
            height = screenHeightPx.toInt() )
    }) {
        it.resume()
        it.onGameOver = {
           onGameOver()

        }
    }
}
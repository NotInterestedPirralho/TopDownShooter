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
import com.example.topdownshooter.ui.theme.TopDownShooterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TopDownShooterTheme {
                var playerOffset by remember { mutableStateOf(Offset.Zero) }
                val player = remember { Player(context = this, width = 1080, height = 1920) }

                JoystickView(
                    modifier = Modifier.fillMaxSize(),
                    onMove = { offset ->
                        playerOffset = offset
                        player.updateWithJoystick(offset)
                    }
                )
            }
        }
    }
}
package com.example.topdownshooter

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun JoystickView(
    modifier: Modifier = Modifier,
    onMove: (Offset) -> Unit
) {
    var center by remember { mutableStateOf(Offset.Zero) }
    var handlePosition by remember { mutableStateOf(Offset.Zero) }

    Canvas(modifier = modifier
        .pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                /*handlePosition = (handlePosition + dragAmount).coerceIn(
                    Offset(-100f, -100f),
                    Offset(100f, 100f)
                )*/
                onMove(handlePosition)
                change.consume()
            }
        }
    ) {
        center = center.copy(x = size.width / 2, y = size.height / 2)
        drawCircle(Color.Gray, radius = 100.dp.toPx(), center = center)
        drawCircle(Color.Red, radius = 20.dp.toPx(), center = center + handlePosition)
    }
}
package com.example.topdownshooter.entities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import com.example.topdownshooter.R


class Bullet {
    var x = 0f
    var y = 0f
    var speed = 25f // Speed of the bullet
    var directionX = 0f
    var directionY = 0f

    var bitmap: Bitmap // Image for the bullet
    var detectCollisions: Rect

    constructor(context: Context, startX: Int, startY: Int, aimX: Float, aimY: Float, width: Int, height: Int) {
        x = startX.toFloat()
        y = startY.toFloat()

        // Normalize the aim direction
        val magnitude = Math.sqrt((aimX * aimX + aimY * aimY).toDouble()).toFloat()
        if (magnitude != 0f) {
            directionX = aimX / magnitude
            directionY = aimY / magnitude
        }

        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.bullet).let {
            Bitmap.createScaledBitmap(it, width / 18, height / 12, false)
        }
        detectCollisions = Rect(x.toInt(), y.toInt(), (x + bitmap.width).toInt(), (y + bitmap.height).toInt())
    }

    fun update() {
        // Move the bullet in the direction of the aim
        x += directionX * speed
        y += directionY * speed

        // Update collision detection
        detectCollisions.left = x.toInt()
        detectCollisions.top = y.toInt()
        detectCollisions.right = (x + bitmap.width).toInt()
        detectCollisions.bottom = (y + bitmap.height).toInt()
    }

}

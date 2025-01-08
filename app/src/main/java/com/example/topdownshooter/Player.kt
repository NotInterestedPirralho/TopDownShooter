package com.example.topdownshooter


import android.graphics.Bitmap
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.Rect
import androidx.compose.ui.geometry.Offset

class Player {

    var rotationAngle = 0f


    var x = 0f
    var y = 0f
    var maxX = 0
    var maxY = 0
    var minX = 0
    var minY = 0

    var bitmap : Bitmap
    var speed = 10
    var detectCollision : Rect

    constructor(context: Context, width: Int, height: Int){
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player).let{Bitmap.createScaledBitmap(it, width/14, height/8, false)}

        minX = 0
        maxX = width

        maxY = height - bitmap.height
        minY = 0

        x = width/2-width/14/2.toFloat()
        y = height/2-height/8/2.toFloat()



        detectCollision = Rect(x.toInt(), y.toInt(), bitmap.width, bitmap.height)
    }

    fun setRotation(directionX: Float, directionY: Float) {
        // Calculate the angle in degrees from the direction vector
        rotationAngle = Math.toDegrees(Math.atan2(directionY.toDouble(), directionX.toDouble())).toFloat()

        // Ensure the angle is between 0 and 360
        if (rotationAngle < 0) {
            rotationAngle += 360
        }
    }

    fun getRotatedBitmap(): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(rotationAngle, (bitmap.width / 2).toFloat(), (bitmap.height / 2).toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }


    fun update(){


        if (x < minX) x = minX.toFloat()
        if (x > maxX) x = maxX.toFloat()
        if (y < minY) y = minY.toFloat()
        if (y > maxY) y = maxY.toFloat()

        updateCollisionBounds()
    }

    fun updateCollisionBounds() {
        val rotatedBitmap = getRotatedBitmap()
        detectCollision.left = x.toInt()
        detectCollision.top = y.toInt()
        detectCollision.right = x.toInt() + rotatedBitmap.width
        detectCollision.bottom = y.toInt() + rotatedBitmap.height
    }


}
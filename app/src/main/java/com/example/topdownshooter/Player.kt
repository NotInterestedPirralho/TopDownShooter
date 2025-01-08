package com.example.topdownshooter


import android.graphics.Bitmap
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Rect
import androidx.compose.ui.geometry.Offset

class Player {

    var x = 0
    var y = 0
    var maxX = 0
    var maxY = 0
    var minX = 0
    var minY = 0

    var bitmap : Bitmap

    var detectCollision : Rect

    constructor(context: Context, width: Int, height: Int){
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player).let{Bitmap.createScaledBitmap(it, width/14, height/8, false)}

        minX = 0
        maxX = width

        maxY = height - bitmap.height
        minY = 0

        x = width/2-width/14/2
        y = height/2-height/8/2



        detectCollision = Rect(x, y, bitmap.width, bitmap.height)
    }

    fun update(){


        if (x < minX) x = minX
        if (x > maxX) x = maxX
        if (y < minY) y = minY
        if (y > maxY) y = maxY

        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height
    }

}
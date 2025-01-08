package com.example.topdownshooter.entities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.Rect
import com.example.topdownshooter.R
import java.util.Random

class Enemy {


    var x = 0
    var y = 0
    var speed = 0
    var maxX = 0
    var maxY = 0
    var minX = 0
    var minY = 0

    var bitmap : Bitmap
    var rotationAngle = 0f // Angle to rotate the bitmap based on direction


    var direction: Direction

    val generator = Random()

    var detectCollision : Rect


    enum class Direction {
        LEFT, RIGHT, TOP, BOTTOM
    }

    constructor(context: Context, width: Int, height: Int){
    bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.enemy).let{Bitmap.createScaledBitmap(it, width/16, height/8, false)}

    minX = 1
    maxX = width

    maxY = height
    minY = 1


        direction = Direction.values()[generator.nextInt(Direction.values().size)]
        setRotationAngle()
        speed = generator.nextInt(6) + 10

        when (direction) {
            Direction.LEFT -> {
                x = maxX
                y = generator.nextInt(maxY)
            }
            Direction.RIGHT -> {
                x = minX
                y = generator.nextInt(maxY)
            }
            Direction.TOP -> {
                x = generator.nextInt(maxX)
                y = minY
            }
            Direction.BOTTOM -> {
                x = generator.nextInt(maxX)
                y = maxY
            }
        }

        detectCollision = Rect(x, y, bitmap.width, bitmap.height)
    }



    fun update() {
        when (direction) {
            Direction.LEFT -> x -= (speed)
            Direction.RIGHT -> x += (speed)
            Direction.TOP -> y += (speed)
            Direction.BOTTOM -> y -= (speed)
        }

        if (x < minX - bitmap.width || x > maxX || y < minY - bitmap.height || y > maxY) {
            resetPosition()
        }

        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height
    }

    private fun resetPosition() {
        direction = Direction.values()[generator.nextInt(Direction.values().size)]
        setRotationAngle()
        speed = generator.nextInt(3) + 4

        when (direction) {
            Direction.LEFT -> {
                x = maxX
                y = generator.nextInt(maxY)
            }
            Direction.RIGHT -> {
                x = minX - bitmap.width
                y = generator.nextInt(maxY)
            }
            Direction.TOP -> {
                x = generator.nextInt(maxX)
                y = minY - bitmap.height
            }
            Direction.BOTTOM -> {
                x = generator.nextInt(maxX)
                y = maxY
            }
        }
    }
    private fun setRotationAngle() {
        rotationAngle = when (direction) {
            Direction.LEFT -> 180f
            Direction.RIGHT -> 0f
            Direction.TOP -> 90f
            Direction.BOTTOM -> 270f
        }
    }

    fun getRotatedBitmap(): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(rotationAngle, (bitmap.width / 2).toFloat(), (bitmap.height / 2).toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
}
package com.example.topdownshooter.entities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
    var boosting = false

    var direction: Direction

    val generator = Random()

    var detectCollision : Rect


    enum class Direction {
        LEFT, RIGHT, TOP, BOTTOM
    }

    init {
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.enemy)

        minX = 0
        maxX = width

        maxY = height - bitmap.height
        minY = 0

        direction = Direction.values()[generator.nextInt(Direction.values().size)]
        speed = generator.nextInt(6) + 10

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

        detectCollision = Rect(x, y, bitmap.width, bitmap.height)
    }

    /*constructor(context: Context, width: Int, height: Int){
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.enemy)

        minX = 0
        maxX = width

        maxY = height - bitmap.height
        minY = 0

        x = maxX
        y = generator.nextInt(maxY)

        speed = generator.nextInt(6) + 10

        detectCollision = Rect(x, y, bitmap.width, bitmap.height)
    }*/

    fun update(playerSpeed: Int) {
        when (direction) {
            Direction.LEFT -> x -= (playerSpeed + speed)
            Direction.RIGHT -> x += (playerSpeed + speed)
            Direction.TOP -> y += (playerSpeed + speed)
            Direction.BOTTOM -> y -= (playerSpeed + speed)
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
        speed = generator.nextInt(6) + 10

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
}
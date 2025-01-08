package com.example.topdownshooter

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.graphics.Color
import android.graphics.Paint
import android.util.SparseArray
import android.view.MotionEvent
import com.example.topdownshooter.entities.Enemy
import kotlin.text.toFloat

class GameView : SurfaceView, Runnable {

    var playing = false
    var gameThread: Thread? = null
    lateinit var surfaceHolder: SurfaceHolder
    lateinit var canvas: Canvas
    lateinit var player: Player
    lateinit var paint: Paint
    var enemies = arrayListOf<Enemy>()

    private val activeTouches = SparseArray<Boolean>()


    private fun init(context: Context, width: Int, height: Int) {
        surfaceHolder = holder
        paint = Paint()
        player = Player(context, width, height)

        for (i in 0..2) {
            enemies.add(Enemy(context, width, height))
        }
    }

    constructor(context: Context?, width: Int, height: Int) : super(context) {
        init(context!!, width, height)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context!!, 0, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context!!, 0, 0)
    }


    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread?.start()
    }

    fun pause() {
        playing = false
        gameThread?.join()
    }

    override fun run() {
        while (playing) {
            update()
            draw()
            control()
        }
    }

    fun update() {
        player.update()

        for (e in enemies) {
            e.update(player.speed)
        }
    }
        fun draw() {
            if (surfaceHolder.surface.isValid) {
                canvas = surfaceHolder.lockCanvas()
                canvas.drawColor(Color.DKGRAY)
                paint.color = Color.YELLOW

                canvas.drawBitmap(player.bitmap, player.x.toFloat(), player.y.toFloat(), paint)

                for (e in enemies) {
                    canvas.drawBitmap(e.bitmap, e.x.toFloat(), e.y.toFloat(), paint)
                }



                surfaceHolder.unlockCanvasAndPost(canvas)
            }
        }


        var callGameOverOnce = false

        fun control() {
            Thread.sleep(17)

        }

        override fun onTouchEvent(event: MotionEvent?): Boolean {
            event?.let {
                val pointerCount = event.pointerCount


                when (event?.actionMasked) {

                    MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                        val index = event.actionIndex
                        val pointerId = event.getPointerId(index)
                        val x = event.getX(index)
                        val y = event.getY(index)

                    }

                    MotionEvent.ACTION_MOVE -> {
                        for (i in 0 until pointerCount) {
                            val pointerId = event.getPointerId(i)
                            val isBoosting = activeTouches[pointerId] ?: false


                        }
                    }

                    MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                        val index = event.actionIndex
                        val pointerId = event.getPointerId(index)
                        activeTouches.remove(pointerId)

                    }
                }
            }
            return true
        }
    }




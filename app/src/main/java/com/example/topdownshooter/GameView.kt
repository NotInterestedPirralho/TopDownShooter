package com.example.topdownshooter

import android.content.Context
import android.graphics.Canvas
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.graphics.Color

class GameView : SurfaceView, Runnable {

    var playing = false
    var gameThread: Thread? = null
    lateinit var surfaceHolder: SurfaceHolder
    lateinit var canvas: Canvas

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

    private fun init(context: Context, width: Int, height: Int) {
        surfaceHolder = holder
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

    override fun run(){
        while(playing){
            update()
            draw()
            control()
        }
    }

    fun update(){

    }
    fun draw() {
        if (surfaceHolder.surface.isValid) {
            canvas = surfaceHolder.lockCanvas()
            canvas.drawColor(Color.BLACK)
            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    var callGameOverOnce = false
    fun control(){
        Thread.sleep(17)

    }
}
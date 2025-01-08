package com.example.topdownshooter

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Handler
import android.os.Looper
import android.util.SparseArray
import android.view.MotionEvent
import com.example.topdownshooter.entities.Bullet
import com.example.topdownshooter.entities.Enemy

class GameView : SurfaceView, Runnable {

    var playing = false
    var gameThread: Thread? = null
    lateinit var surfaceHolder: SurfaceHolder
    lateinit var canvas: Canvas
    lateinit var player: Player
    lateinit var paint: Paint
    lateinit var paint2: Paint
    lateinit var paint3: Paint

    private lateinit var movementJoystick: Joystick
    private lateinit var aimAndShootJoystick: Joystick
    var Score = 0

    var enemies = arrayListOf<Enemy>()
    var bullets = arrayListOf<Bullet>()
    val bulletstoremove = mutableListOf<Bullet>()
    private var lastShotTime: Long = 0 // Tracks the last time a shot was fired
    private val shootingDelay: Long = 500 // Delay between shots in milliseconds
    var life = 1
    var onGameOver : () -> Unit = {}

    fun shootBullet(directionX: Float, directionY: Float) {
        val bullet = Bullet(context,(player.x).toInt(), (player.y).toInt(), directionX, directionY,width,height)
        bullets.add(bullet)
    }


    private fun init(context: Context, width: Int, height: Int) {
        surfaceHolder = holder
        paint = Paint()
        paint2 = Paint()
        paint3 = Paint()
        player = Player(context, width, height)
        movementJoystick = Joystick(300f, height - 300f, 200f) // Bottom-left
        aimAndShootJoystick = Joystick(width - 300f, height - 300f, 200f) // Bottom-right


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
        val (moveX, moveY) = movementJoystick.getNormalizedVector()
        player.x += moveX * player.speed
        player.y += moveY * player.speed

        for (bullet in bulletstoremove){
            bullets.remove(bullet)

        }

        for (bullet in bullets) {
            bullet.update()
        }

        val (aimX, aimY) = aimAndShootJoystick.getNormalizedVector()
        if (aimAndShootJoystick.isActive) {
            // Rotate the player towards the aim direction
            player.setRotation(aimX, aimY) // Adjust this based on your rotation logic

            // Check if it's time to shoot
            if (System.currentTimeMillis() - lastShotTime > shootingDelay) {
                shootBullet(aimX, aimY) // Implement bullet shooting logic
                lastShotTime = System.currentTimeMillis() // Update last shot time
            }
        }



        for (e in enemies) {
            e.update()

            if (Rect.intersects(player.detectCollision, e.detectCollision)){
                life = 0

            }

            for (bullet in bullets) {
                if (Rect.intersects(e.detectCollision, bullet.detectCollisions)) {
                    e.x = -600
                    bulletstoremove.add(bullet)
                    Score += 1
                }

                if (bullet.x > width) {
                    bulletstoremove.add(bullet)
                }
            }
        }
    }


        fun draw() {
            if (surfaceHolder.surface.isValid) {
                canvas = surfaceHolder.lockCanvas()
                canvas.drawColor(Color.DKGRAY)
                paint.color = Color.YELLOW
                paint.textSize = 60f

                val rotatedBitmap = player.getRotatedBitmap()
                canvas.drawBitmap(rotatedBitmap, player.x.toFloat(), player.y.toFloat(), paint)


                for (e in enemies) {
                    val rotatedBitmap = e.getRotatedBitmap()
                    canvas.drawBitmap(rotatedBitmap, e.x.toFloat(), e.y.toFloat(), paint)
                }

                paint2.color = Color.LTGRAY
                paint3.color = Color.BLACK
                // Draw movement joystick
                canvas.drawCircle(
                    movementJoystick.baseX,
                    movementJoystick.baseY,
                    movementJoystick.radius,
                    paint2
                )
                canvas.drawCircle(movementJoystick.thumbX, movementJoystick.thumbY, 80f, paint3)

                // Draw aim-and-shoot joystick
                canvas.drawCircle(
                    aimAndShootJoystick.baseX,
                    aimAndShootJoystick.baseY,
                    aimAndShootJoystick.radius,
                    paint2
                )
                canvas.drawCircle(
                    aimAndShootJoystick.thumbX,
                    aimAndShootJoystick.thumbY,
                    80f,
                    paint3
                )

                for (bullet in bullets) {
                    canvas.drawBitmap(bullet.bitmap, bullet.x, bullet.y, paint)
                }

                canvas.drawText("Score: ${Score}", 20f, 60f, paint)



                surfaceHolder.unlockCanvasAndPost(canvas)
            }

        }

    var callGameOverOnce = false

    fun control() {
            Thread.sleep(17)
            if (life == 0 ){
                playing = false
                Handler(Looper.getMainLooper()).post {
                    if (!callGameOverOnce) {
                        onGameOver()
                        callGameOverOnce = true
                    }
                    gameThread?.join()
                }
                }

        }

        override fun onTouchEvent(event: MotionEvent?): Boolean {
            event?.let {
                val pointerCount = event.pointerCount


                for (i in 0 until pointerCount) {
                    val x = it.getX(i)
                    val y = it.getY(i)

                    if (isWithinJoystick(movementJoystick, x, y)) {
                        movementJoystick.update(x, y)
                        movementJoystick.isActive = true
                    } else if (isWithinJoystick(aimAndShootJoystick, x, y)) {
                        aimAndShootJoystick.update(x, y)
                        aimAndShootJoystick.isActive = true
                    }
                }

                when (event.actionMasked) {
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                        val index = event.actionIndex
                        val pointerId = event.getPointerId(index)

                        // Reset joystick when the finger is lifted
                        if (pointerId == 0) movementJoystick.reset()
                        if (pointerId == 1) aimAndShootJoystick.reset()
                    }
                }
            }
            return true
        }




}




private fun isWithinJoystick(joystick: Joystick, x: Float, y: Float): Boolean {
    val dx = x - joystick.baseX
    val dy = y - joystick.baseY
    return Math.sqrt((dx * dx + dy * dy).toDouble()) <= joystick.radius}




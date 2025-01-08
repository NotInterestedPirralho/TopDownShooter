package com.example.topdownshooter

class Joystick(val baseX: Float, val baseY: Float, val radius: Float) {
    var thumbX: Float = baseX
    var thumbY: Float = baseY
    var isActive = false

    fun update(x: Float, y: Float) {
        val dx = x - baseX
        val dy = y - baseY
        val distance = Math.sqrt((dx * dx + dy * dy).toDouble()).toFloat()

        if (distance > radius) {
            // Clamp the thumb position to the joystick boundary
            thumbX = baseX + dx / distance * radius
            thumbY = baseY + dy / distance * radius
        } else {
            thumbX = x
            thumbY = y
        }
    }

    fun reset() {
        thumbX = baseX
        thumbY = baseY
        isActive = false
    }

    fun getNormalizedVector(): Pair<Float, Float> {
        val dx = thumbX - baseX
        val dy = thumbY - baseY
        val magnitude = Math.sqrt((dx * dx + dy * dy).toDouble()).toFloat()

        return if (magnitude > 0) {
            Pair(dx / magnitude, dy / magnitude)
        } else {
            Pair(0f, 0f)
        }
    }
}

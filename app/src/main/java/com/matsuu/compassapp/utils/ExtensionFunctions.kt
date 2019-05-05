package com.matsuu.compassapp.utils

// Number conversion
fun Float.convertAzimuthFromRadiansToDegrees() : Float =
    when {
        this > 0 -> this.radiansToDegrees()
        this < 0 -> 360 + this.radiansToDegrees()
        else -> 0f
    }

private fun Float.radiansToDegrees() : Float =
    ((this/Math.PI) * 180).toFloat()

// end Number conversion
package com.orpuwupetup.compassapp.utils.providers

import android.view.animation.Animation
import android.view.animation.RotateAnimation

class CompassAnimationProvider {

    fun provideCompassRotationAnimation(fromDegrees: Float, toDegrees: Float) =
        RotateAnimation(
            fromDegrees,
            toDegrees,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = 210
            fillAfter = true
        }
}
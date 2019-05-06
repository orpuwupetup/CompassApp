package com.matsuu.compassapp.data.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener

// adapter created so that we don't have to override both of this functions in our implementation of sensor event listener
open class SensorEventListenerAdapter : SensorEventListener {

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // do nothing
    }

    override fun onSensorChanged(event: SensorEvent?) {
        // do nothing
    }
}
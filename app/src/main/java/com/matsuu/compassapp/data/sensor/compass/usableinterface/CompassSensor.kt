package com.matsuu.compassapp.data.sensor.compass.usableinterface

import com.matsuu.compassapp.data.sensor.compass.AndroidCompassSensor

interface CompassSensor {

    fun registerSensorListener()
    fun unregisterSensorListener()
    fun setPhoneOrientationChangeListener(doOnAzimuthChanged: (newAzimuthDegrees: Float) -> Unit)
    fun setPhoneOrientationChangeListener(listener: AndroidCompassSensor.PhoneOrientationChangeListener?)
}
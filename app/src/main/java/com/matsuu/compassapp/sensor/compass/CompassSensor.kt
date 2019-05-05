package com.matsuu.compassapp.sensor.compass

interface CompassSensor {

    fun registerSensorListener()
    fun unregisterSensorListener()
    fun setPhoneOrientationChangeListener(doOnAzimuthChanged: (newAzimuthDegrees: Float) -> Unit)
    fun setPhoneOrientationChangeListener(listener: AndroidCompassSensor.PhoneOrientationChangeListener?)
}
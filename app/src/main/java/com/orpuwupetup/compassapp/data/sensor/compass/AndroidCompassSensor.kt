package com.orpuwupetup.compassapp.data.sensor.compass

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import com.orpuwupetup.compassapp.data.sensor.SensorEventListenerAdapter
import com.orpuwupetup.compassapp.data.sensor.compass.usableinterface.CompassSensor
import com.orpuwupetup.compassapp.utils.convertAzimuthFromRadiansToDegrees
import timber.log.Timber

class AndroidCompassSensor(private val sensorManager: SensorManager) : SensorEventListenerAdapter(),
    CompassSensor {

    private val accelerometerReading = FloatArray(3)
    private val magnetometerReading = FloatArray(3)

    private val rotationMatrix = FloatArray(9)
    private val orientationAngles = FloatArray(3)

    private var phoneOrientationChangeListener: PhoneOrientationChangeListener? = null

    // sensor related code
    override fun registerSensorListener() {

        /*
        I'm using this approach for getting device orientation, because Sensor.TYPE_ORIENTATION is deprecated since API
        level 15, due to its low accuracy when roll angle of the device is different than 0
        */
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { accelerometer ->
            sensorManager.registerListener(
                this,
                accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL,
                SensorManager.SENSOR_DELAY_UI
            )
        }

        sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)?.also { magneticField ->
            sensorManager.registerListener(
                this,
                magneticField,
                SensorManager.SENSOR_DELAY_NORMAL,
                SensorManager.SENSOR_DELAY_UI
            )
        }
    }

    override fun unregisterSensorListener() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let { sensorEvent ->
            if (sensorEvent.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                System.arraycopy(sensorEvent.values, 0, accelerometerReading, 0, accelerometerReading.size)
            } else if (sensorEvent.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
                System.arraycopy(sensorEvent.values, 0, magnetometerReading, 0, magnetometerReading.size)

                updateOrientationAngles()
            }
        }
    }

    private fun updateOrientationAngles() {
        SensorManager.getRotationMatrix(
            rotationMatrix,
            null,
            accelerometerReading,
            magnetometerReading
        )

        SensorManager.getOrientation(rotationMatrix, orientationAngles)

        notifyListeners()
    }
    // end sensor related code

    // Orientation changed listeners related code
    private fun notifyListeners() {
        val newAzimuthInDegrees = orientationAngles[0].convertAzimuthFromRadiansToDegrees()

        Timber.d(
            "azimuth: $newAzimuthInDegrees, pitch: ${orientationAngles[1]}, roll: ${orientationAngles[2]}"
        )

        phoneOrientationChangeListener?.onFacedAzimuthChanged(newAzimuthInDegrees)
    }

    /*
    I think this method of setting listeners is much more clear during usage, and requires less boiler plate code,
    Kotlin FTW!
    */
    override fun setPhoneOrientationChangeListener(doOnAzimuthChanged: (newAzimuthDegrees: Float) -> Unit) {
        phoneOrientationChangeListener = phoneOrientationChangeListener ?: object : PhoneOrientationChangeListener {
            override fun onFacedAzimuthChanged(newAzimuthDegrees: Float) {
                doOnAzimuthChanged(newAzimuthDegrees)
            }
        }
    }

    override fun setPhoneOrientationChangeListener(listener: PhoneOrientationChangeListener?) {
        this.phoneOrientationChangeListener = listener
    }

    interface PhoneOrientationChangeListener {
        fun onFacedAzimuthChanged(newAzimuthDegrees: Float)
    }
    // end Orientation changed listeners related code
}
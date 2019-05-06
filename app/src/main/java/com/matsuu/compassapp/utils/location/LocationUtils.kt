package com.matsuu.compassapp.utils.location

import android.hardware.GeomagneticField
import android.location.Location
import android.location.LocationManager
import java.nio.file.FileSystemNotFoundException

class LocationUtils {

    companion object {
        fun createLocation(latitude: Float, longitude: Float) =
                Location(LocationManager.GPS_PROVIDER).apply {
                    this.latitude = latitude.toDouble()
                    this.longitude = longitude.toDouble()
                }

        fun calculateMagneticDeclination(userLocation: Location): Float {

            val geoField = GeomagneticField(
                userLocation.latitude.toFloat(),
                userLocation.longitude.toFloat(),
                userLocation.altitude.toFloat(),
                System.currentTimeMillis())
            return geoField.declination
        }
    }
}
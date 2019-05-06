package com.matsuu.compassapp.utils.location

import android.location.Location
import android.location.LocationManager

class LocationUtils {

    companion object {
        fun createLocation(latitude: Float, longitude: Float) =
                Location(LocationManager.GPS_PROVIDER).apply {
                    this.latitude = latitude.toDouble()
                    this.longitude = longitude.toDouble()
                }
    }
}
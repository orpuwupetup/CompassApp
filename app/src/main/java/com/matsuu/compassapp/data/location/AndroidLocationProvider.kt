package com.matsuu.compassapp.data.location

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.*
import timber.log.Timber

class AndroidLocationProvider(private val fusedLocationProviderClient: FusedLocationProviderClient) {

    companion object {
        fun createLocationRequest() = LocationRequest.create()?.apply {
                interval = 10000
                fastestInterval = 5000
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    @SuppressLint("MissingPermission")
    fun getLastKnownLocation() {

        // get last known location
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                Timber.e("latitude: ${location.latitude}, longitude: ${location.longitude}")

            }
        }
    }


}
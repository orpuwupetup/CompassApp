package com.matsuu.compassapp.data.location

import android.location.Location

interface LocationProvider {

    fun setLocationUpdatesListener(onLocationProvided: (Location) -> Unit)
    fun stopLocationUpdates()
    fun startLocationUpdates()
    fun setLocationUpdatesListener(listener: AndroidLocationProvider.LocationUpdatesListener?)
}
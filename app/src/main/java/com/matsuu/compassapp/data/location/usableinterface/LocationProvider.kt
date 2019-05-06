package com.matsuu.compassapp.data.location.usableinterface

import android.location.Location
import com.matsuu.compassapp.data.location.AndroidLocationProvider

interface LocationProvider {

    fun setLocationUpdatesListener(onLocationProvided: (Location) -> Unit)
    fun stopLocationUpdates()
    fun startLocationUpdates()
    fun setLocationUpdatesListener(listener: AndroidLocationProvider.LocationUpdatesListener?)
}
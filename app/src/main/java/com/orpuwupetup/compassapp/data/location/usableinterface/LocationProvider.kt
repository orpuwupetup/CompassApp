package com.orpuwupetup.compassapp.data.location.usableinterface

import android.location.Location
import com.orpuwupetup.compassapp.data.location.AndroidLocationProvider

interface LocationProvider {

    fun setLocationUpdatesListener(onLocationProvided: (Location) -> Unit)
    fun stopLocationUpdates()
    fun startLocationUpdates()
    fun setLocationUpdatesListener(listener: AndroidLocationProvider.LocationUpdatesListener?)
}
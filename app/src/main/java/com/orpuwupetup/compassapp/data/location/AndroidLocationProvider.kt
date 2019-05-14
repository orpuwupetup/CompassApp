package com.orpuwupetup.compassapp.data.location

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.orpuwupetup.compassapp.data.location.usableinterface.LocationProvider
import timber.log.Timber

class AndroidLocationProvider(private val fusedLocationProviderClient: FusedLocationProviderClient) :
    LocationProvider {

    companion object {
        fun createLocationRequest() = LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    private var currentLocation: Location? = null
    private var locationCallback: LocationCallback? = null
    private var locationRequest: LocationRequest? = null

    private var locationUpdatesListener: LocationUpdatesListener? = null

    private fun setUpLocationProvider() {
        // get and save last known location to prompt location manager and FusedLocationProvider
        getLastKnownLocation()

        // set new location request and callback only if there are currently non
        locationRequest = locationRequest ?: createLocationRequest()
        locationCallback = locationCallback ?: object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return

                for (location in locationResult.locations) {

                    // notify listener
                    locationUpdatesListener?.onLocationProvided(location)
                    Timber.d("latitude: ${location.latitude}, longitude: ${location.longitude}")
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation() {
        // get last known location
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
            // if there is last known location, notify listener about it immediately so that user don't have to wait for
            // next location update and have better experience with using this app
            location?.let {  lastKnownLocation ->
                locationUpdatesListener?.onLocationProvided(lastKnownLocation)
            }
            currentLocation = location
        }
    }

    @SuppressLint("MissingPermission")
    override fun startLocationUpdates() {
        setUpLocationProvider()
        fusedLocationProviderClient.requestLocationUpdates(createLocationRequest(), locationCallback, null)
    }

    override fun stopLocationUpdates() {
        locationCallback?.let {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        }
    }

    override fun setLocationUpdatesListener(listener: LocationUpdatesListener?) {
        locationUpdatesListener = listener
    }

    override fun setLocationUpdatesListener(onLocationProvided: (Location) -> Unit) {
        locationUpdatesListener = object : LocationUpdatesListener {
            override fun onLocationProvided(location: Location) {
                onLocationProvided(location)
            }
        }
    }

    interface LocationUpdatesListener {
        fun onLocationProvided(location: Location)
    }
}
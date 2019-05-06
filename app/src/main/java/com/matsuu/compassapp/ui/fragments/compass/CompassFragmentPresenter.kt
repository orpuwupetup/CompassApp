package com.matsuu.compassapp.ui.fragments.compass

import android.location.Location
import android.location.LocationManager
import com.matsuu.compassapp.data.location.LocationProvider
import com.matsuu.compassapp.data.sensor.compass.CompassSensor
import com.matsuu.compassapp.utils.location.LocationUtils
import timber.log.Timber
import javax.inject.Inject

class CompassFragmentPresenter @Inject constructor(
    private val compassSensor: CompassSensor,
    private val androidLocationProvider: LocationProvider
) :
    CompassFragmentContract.Presenter {

    private var view: CompassFragmentContract.View? = null

    private var lastCompassRotationDegree = 0f
    private var currentCompassRotationDegree = 0f

    private var currentDestinationLatitude = 0f
    private var currentDestinationLongitude = 0f

    private var isNavigating = false

    override fun takeView(view: CompassFragmentContract.View) {
        this.view = view

        setCompassListeners()

        setUpLocationUpdates()
    }

    private fun setUpLocationUpdates() {
        if (isNavigating)
            with(androidLocationProvider){
                setLocationUpdatesListener { userLocation ->
                    Timber.e("${userLocation.longitude}")

                    setUpNavigation(userLocation)
                }
                startLocationUpdates()
            }
    }

    private fun setUpNavigation(userLocation: Location) {


        //TODO show navigation, rotate accordingly
        val destinationLocation = LocationUtils.createLocation(currentDestinationLatitude, currentDestinationLongitude)


        Timber.e("bearing to destination: ${userLocation.bearingTo(destinationLocation)}")
    }

    private fun setCompassListeners() {
        with(compassSensor) {

            setPhoneOrientationChangeListener { currentlyFacedAzimuth ->
                /*
                it equals inverted faced azimuth, because we want to rotate compass wind rose view in the opposite direction,
                so that N will be actually facing magnetic north pole
                */
                currentCompassRotationDegree = -currentlyFacedAzimuth

                view?.rotateCompass(lastCompassRotationDegree, currentCompassRotationDegree)

                lastCompassRotationDegree = -currentlyFacedAzimuth
            }

            registerSensorListener()
        }
    }

    override fun startNavigation(lat: Float, long: Float) {
        isNavigating = true

        currentDestinationLatitude = lat
        currentDestinationLongitude = long

        setUpLocationUpdates()
    }

    override fun stopNavigation() {
        isNavigating = false

        unregisterLocationListeners()
    }

    override fun dropView() {
        view = null

        unregisterLocationListeners()
        unregisterCompassListeners()
    }

    private fun unregisterLocationListeners() {
        with(androidLocationProvider){
            setLocationUpdatesListener(null)
            stopLocationUpdates()
        }
    }

    private fun unregisterCompassListeners() {
        with(compassSensor) {
            unregisterSensorListener()
            setPhoneOrientationChangeListener(null)
        }
    }
}
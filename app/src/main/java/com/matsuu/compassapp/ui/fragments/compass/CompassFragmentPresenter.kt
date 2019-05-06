package com.matsuu.compassapp.ui.fragments.compass

import android.location.Location
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
    private var lastNavigationCursorRotationDegree = 0f
    private var currentNavigationCursorRotationDegree = 0f
    private var currentMagneticDeclination = 0f

    // approximate initial bearing in degrees East of true North when traveling along the shortest path between user
    // location and provided destination
    private var currentBearingFromUserLocationToDestination = 0f

    override fun takeView(view: CompassFragmentContract.View) {
        this.view = view

        setCompassListeners()

        setUpLocationUpdates()
    }

    private fun setUpLocationUpdates() {
        if (isNavigating)
            with(androidLocationProvider) {
                setLocationUpdatesListener { userLocation ->
                    Timber.e("${userLocation.longitude}")

                    calculateBearingAndMagneticDeclination(userLocation)

                }
                startLocationUpdates()
            }
    }

    private fun calculateBearingAndMagneticDeclination(userLocation: Location) {

        val destinationLocation = LocationUtils.createLocation(currentDestinationLatitude, currentDestinationLongitude)

        currentBearingFromUserLocationToDestination = userLocation.bearingTo(destinationLocation)
        currentMagneticDeclination = LocationUtils.calculateMagneticDeclination(userLocation)
    }

    private fun setCompassListeners() {
        with(compassSensor) {

            setPhoneOrientationChangeListener { currentlyFacedAzimuth ->
                /*
                it equals inverted faced azimuth, because we want to rotate compass wind rose view in the opposite direction,
                so that N will be actually facing magnetic north pole
                */
                currentCompassRotationDegree = -currentlyFacedAzimuth

                if (isNavigating) {
                    rotateNavigationCursor(currentCompassRotationDegree)
                }

                view?.rotateCompass(lastCompassRotationDegree, currentCompassRotationDegree)

                lastCompassRotationDegree = -currentlyFacedAzimuth
            }

            registerSensorListener()
        }
    }

    private fun rotateNavigationCursor(currentCompassRotationDegree: Float) {
        currentNavigationCursorRotationDegree = calculateNavigationCursorRotation(currentCompassRotationDegree)

        view?.rotateNavigationCursor(lastNavigationCursorRotationDegree, currentNavigationCursorRotationDegree)

        lastNavigationCursorRotationDegree = currentNavigationCursorRotationDegree
    }

    private fun calculateNavigationCursorRotation(currentCompassRotationDegree: Float): Float =
    /*
    navigation cursor rotation equals inversion (same as with wind rose, we want to actually rotate cursor in opposite
    direction of what we want to point at) of true north (magnetic north, which is inversion of compass rotation,
    plus magnetic declination) + bearing to
    */
        -((-currentCompassRotationDegree) + currentMagneticDeclination + currentBearingFromUserLocationToDestination)

    override fun startNavigation(lat: Float, long: Float) {
        isNavigating = true
        view?.showNavigationCursor()

        currentDestinationLatitude = lat
        currentDestinationLongitude = long

        setUpLocationUpdates()
    }

    override fun stopNavigation() {
        isNavigating = false
        view?.hideNavigationCursor()
        unregisterLocationListeners()
    }

    override fun dropView() {
        view = null

        unregisterLocationListeners()
        unregisterCompassListeners()
    }

    private fun unregisterLocationListeners() {
        with(androidLocationProvider) {
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
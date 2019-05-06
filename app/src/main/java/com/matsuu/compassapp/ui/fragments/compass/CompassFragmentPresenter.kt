package com.matsuu.compassapp.ui.fragments.compass

import com.matsuu.compassapp.data.location.AndroidLocationProvider
import com.matsuu.compassapp.data.sensor.compass.CompassSensor
import javax.inject.Inject

class CompassFragmentPresenter @Inject constructor(
    private val compassSensor: CompassSensor,
    private val androidLocationProvider: AndroidLocationProvider) :
    CompassFragmentContract.Presenter {

    private var view: CompassFragmentContract.View? = null

    private var lastCompassRotationDegree = 0f
    private var currentCompassRotationDegree = 0f

    override fun takeView(view: CompassFragmentContract.View) {
        this.view = view

        setCompassListeners()
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
        androidLocationProvider.getLastKnownLocation()
    }

    override fun dropView() {
        view = null

        unregisterCompassListeners()
    }

    private fun unregisterCompassListeners() {
        with(compassSensor) {
            unregisterSensorListener()
            setPhoneOrientationChangeListener(null)
        }
    }
}
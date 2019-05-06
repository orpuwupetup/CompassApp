package com.matsuu.compassapp.ui.fragments.compass

import com.matsuu.compassapp.sensor.compass.CompassSensor
import javax.inject.Inject

class CompassFragmentPresenter @Inject constructor(private val compassSensor: CompassSensor) :
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
                currentCompassRotationDegree = -currentlyFacedAzimuth

                view?.rotateCompass(lastCompassRotationDegree, currentCompassRotationDegree)

                lastCompassRotationDegree = -currentlyFacedAzimuth
            }

            registerSensorListener()
        }
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
package com.matsuu.compassapp.ui.compass


import android.view.View
import com.matsuu.compassapp.R
import com.matsuu.compassapp.ui.AbstractFragment
import com.matsuu.compassapp.ui.compass.usableinterface.Compass
import com.matsuu.compassapp.utils.providers.CompassAnimationProvider
import kotlinx.android.synthetic.main.fragment_compass.*
import timber.log.Timber
import javax.inject.Inject

class CompassFragment : CompassFragmentContract.View, AbstractFragment(),
    Compass {

    @Inject
    lateinit var presenter: CompassFragmentContract.Presenter

    @Inject
    lateinit var animationProvider: CompassAnimationProvider

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }

    override fun getFragmentLayout(): Int = R.layout.fragment_compass

    override fun rotateCompass(lastRotationDegree: Float, currentRotationDegree: Float) {
        Timber.d("degree: $lastRotationDegree")

        compass_part_wind_rose
            .startAnimation(
                animationProvider.provideCompassRotationAnimation(
                    lastRotationDegree,
                    currentRotationDegree))
    }

    override fun rotateNavigationCursor(lastRotationDegree: Float, currentRotationDegree: Float) {
        compass_part_navigation_cursor
            .startAnimation(
                animationProvider.provideCompassRotationAnimation(
                    lastRotationDegree,
                    currentRotationDegree))
    }

    override fun showNavigationCursor() {
        compass_part_navigation_cursor.visibility = View.VISIBLE
    }

    override fun hideNavigationCursor() {
        // clear animation needed so that cursor will disappear even if fill after attribute of rotation animation
        // is set to true
        compass_part_navigation_cursor.clearAnimation()
        compass_part_navigation_cursor.visibility = View.GONE
    }

    override fun startNavigation(lat: Float, long: Float) {
        presenter.startNavigation(lat, long)
    }

    override fun stopNavigation() {
        presenter.stopNavigation()
    }
}
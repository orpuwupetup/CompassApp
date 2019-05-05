package com.matsuu.compassapp.ui.fragments.compass

import com.matsuu.compassapp.R
import com.matsuu.compassapp.ui.fragments.AbstractFragment
import com.matsuu.compassapp.utils.providers.CompassAnimationProvider
import kotlinx.android.synthetic.main.fragment_compass.*
import timber.log.Timber
import javax.inject.Inject

class CompassFragment : CompassFragmentContract.View, AbstractFragment() {

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
}
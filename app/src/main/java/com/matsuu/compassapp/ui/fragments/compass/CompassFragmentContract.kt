package com.matsuu.compassapp.ui.fragments.compass

import com.matsuu.compassapp.ui.BasePresenter
import com.matsuu.compassapp.ui.BaseView

interface CompassFragmentContract {

    interface View: BaseView<Presenter> {
        fun rotateCompass(lastRotationDegree: Float, currentRotationDegree: Float)
    }

    interface Presenter: BasePresenter<View> {
        fun startNavigation(lat: Float, long: Float)
        fun stopNavigation()
    }
}
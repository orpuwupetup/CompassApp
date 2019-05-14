package com.orpuwupetup.compassapp.ui.compass

import com.orpuwupetup.compassapp.ui.BasePresenter
import com.orpuwupetup.compassapp.ui.BaseView

interface CompassFragmentContract {

    interface View: BaseView<Presenter> {
        fun rotateCompass(lastRotationDegree: Float, currentRotationDegree: Float)
        fun rotateNavigationCursor(lastRotationDegree: Float, currentRotationDegree: Float)
        fun showNavigationCursor()
        fun hideNavigationCursor()
    }

    interface Presenter: BasePresenter<View> {
        fun startNavigation(lat: Float, long: Float)
        fun stopNavigation()
    }
}
package com.matsuu.compassapp.ui.fragments.latlonginput

import com.matsuu.compassapp.ui.BasePresenter
import com.matsuu.compassapp.ui.BaseView

interface LatLongInputFragmentContract {

    interface View: BaseView<Presenter> {
        fun hideErrors()
        fun showError(latLongInputError: List<LatLongInputFragment.LatLongInputError>)
        fun notifyListenerAboutCorrectLatLongInput(latitude: Float, longitude: Float)
        fun notifyListenerAboutWrongUserInput()
    }

    interface Presenter: BasePresenter<View> {
        fun latitudeInputChanged(newLatitude: String)
        fun longitudeInputChanged(newLongitude: String)
    }
}
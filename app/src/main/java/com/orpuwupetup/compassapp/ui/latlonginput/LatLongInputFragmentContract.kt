package com.orpuwupetup.compassapp.ui.latlonginput

import com.orpuwupetup.compassapp.ui.BasePresenter
import com.orpuwupetup.compassapp.ui.BaseView

interface LatLongInputFragmentContract {

    interface View: BaseView<Presenter> {
        fun hideErrors()
        fun showErrors(latLongInputError: List<LatLongInputFragment.LatLongInputError>)
        fun notifyListenerAboutCorrectLatLongInput(latitude: Float, longitude: Float)
        fun notifyListenerAboutWrongUserInput()
    }

    interface Presenter: BasePresenter<View> {
        fun latitudeInputChanged(newLatitude: String)
        fun longitudeInputChanged(newLongitude: String)
    }
}
package com.matsuu.compassapp.ui.latlonginput

import javax.inject.Inject

class LatLongInputPresenter @Inject constructor() :
    LatLongInputFragmentContract.Presenter {

    private var view: LatLongInputFragmentContract.View? = null

    private var currentLatitude: String? = null
    private var currentLongitude: String? = null

    private val currentErrors: ArrayList<LatLongInputFragment.LatLongInputError> = arrayListOf()

    override fun takeView(view: LatLongInputFragmentContract.View) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }

    override fun latitudeInputChanged(newLatitude: String) {
        currentLatitude = newLatitude

        checkInput()
    }

    override fun longitudeInputChanged(newLongitude: String) {
        currentLongitude = newLongitude

        checkInput()
    }

    private fun checkInput() {
        // show errors only if either lat or long is provided
        if (currentLatitude.isNullOrBlank() && currentLongitude.isNullOrBlank()) {
            view?.hideErrors()
        } else {

            currentErrors.clear()

            checkLatitude()
            checkLongitude()

            if (currentErrors.isNotEmpty())
                view?.apply {
                    showErrors(currentErrors)
                    notifyListenerAboutWrongUserInput()
                }
            else {
                view?.apply {
                    hideErrors()
                    notifyListenerAboutCorrectLatLongInput(
                        // there are no errors found, so both latitude and longitude won't be null at this point
                        currentLatitude!!.toFloat(),
                        currentLongitude!!.toFloat()
                    )
                }
            }
        }
    }

    private fun checkLongitude() {
        if (!checkIfEmpty(currentLongitude,
                LatLongInputFragment.LatLongInputError.NO_LONGITUDE
            )) {
            // if longitude passed checkIfEmpty it won't be null
            if (currentLongitude!!.toFloat() !in (-180f..180f)) {
                currentErrors.add(LatLongInputFragment.LatLongInputError.WRONG_LONGITUDE)
            }
        }
    }

    private fun checkLatitude() {
        if (!checkIfEmpty(currentLatitude,
                LatLongInputFragment.LatLongInputError.NO_LATITUDE
            )) {
            // if latitude passed checkIfEmpty it won't be null
            if (currentLatitude!!.toFloat() !in (-90f..90f)) {
                currentErrors.add(LatLongInputFragment.LatLongInputError.WRONG_LATITUDE)
            }
        }
    }

    private fun checkIfEmpty(stringToCheck: String?, noInputError: LatLongInputFragment.LatLongInputError): Boolean {
        if (stringToCheck.isNullOrBlank() || containsOnlySpecialSigns(stringToCheck)) {
            currentErrors.add(noInputError)
            return true
        }
        return false
    }

    private fun containsOnlySpecialSigns(stringToCheck: String): Boolean =
        ((stringToCheck.contains(".") || stringToCheck.contains("-"))
                && !stringToCheck.contains("\\d".toRegex()))
}
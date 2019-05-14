package com.orpuwupetup.compassapp.ui.latlonginput

import android.view.View
import com.orpuwupetup.compassapp.R
import com.orpuwupetup.compassapp.ui.AbstractFragment
import com.orpuwupetup.compassapp.ui.latlonginput.usableinterface.LatLongUserInput
import com.orpuwupetup.compassapp.utils.afterTextChanged
import kotlinx.android.synthetic.main.fragment_lat_long_input.*
import javax.inject.Inject

class LatLongInputFragment : LatLongInputFragmentContract.View, AbstractFragment(),
    LatLongUserInput {

    enum class LatLongInputError {
        WRONG_LATITUDE,
        WRONG_LONGITUDE,
        NO_LATITUDE,
        NO_LONGITUDE
    }

    @Inject
    lateinit var errorBuilder: StringBuilder

    @Inject
    lateinit var presenter: LatLongInputFragmentContract.Presenter

    private var onLatLongInputChangedListener: LatLongInputChangedListener? = null

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }

    override fun getFragmentLayout(): Int = R.layout.fragment_lat_long_input

    override fun initViews() {
        setTextWatchers()
    }

    private fun setTextWatchers() {
        textinput_latitude.afterTextChanged { latitudeString ->
            presenter.latitudeInputChanged(latitudeString)
        }

        textinput_longitude.afterTextChanged { longitudeString ->
            presenter.longitudeInputChanged(longitudeString)
        }
    }

    private fun clearErrors() {
        text_input_errors.text = null
        errorBuilder.clear()
    }

    override fun hideErrors() {
        clearErrors()
        text_input_errors.visibility = View.GONE
    }

    override fun showErrors(latLongInputError: List<LatLongInputError>) {
        clearErrors()
        text_input_errors.visibility = View.VISIBLE

        createErrorMessage(latLongInputError)

        text_input_errors.text = errorBuilder.toString()
    }

    private fun createErrorMessage(latLongInputError: List<LatLongInputError>) {
        for (error in latLongInputError) {
            if (errorBuilder.isNotBlank())
                errorBuilder.appendln()
            errorBuilder.append(when(error) {
                LatLongInputError.WRONG_LATITUDE -> getString(R.string.error_wrong_latitude)
                LatLongInputError.WRONG_LONGITUDE -> getString(R.string.error_wrong_longitude)
                LatLongInputError.NO_LATITUDE -> getString(R.string.error_no_latitude)
                LatLongInputError.NO_LONGITUDE -> getString(R.string.error_no_longitude)
            })
        }
    }

    override fun notifyListenerAboutCorrectLatLongInput(latitude: Float, longitude: Float) {
        onLatLongInputChangedListener?.onCorrectLatLongProvided(latitude, longitude)
    }

    override fun notifyListenerAboutWrongUserInput() {
        onLatLongInputChangedListener?.onWrongInputProvided()
    }

    override fun setLatLongInputChangedListener(listener: LatLongInputChangedListener?) {
        this.onLatLongInputChangedListener = listener
    }

    interface LatLongInputChangedListener {
        fun onCorrectLatLongProvided(latitude: Float, longitude: Float)
        fun onWrongInputProvided()
    }
}
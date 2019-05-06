package com.matsuu.compassapp.ui.activities.compass

import android.widget.Toast
import com.matsuu.compassapp.R
import com.matsuu.compassapp.ui.activities.AbstractDefaultActivity
import com.matsuu.compassapp.ui.fragments.latlonginput.LatLongInputFragment
import com.matsuu.compassapp.ui.fragments.latlonginput.LatLongUserInput
import kotlinx.android.synthetic.main.activity_compass.*
import javax.inject.Inject

class CompassActivity : AbstractDefaultActivity(), CompassActivityContract.View, LatLongInputFragment.LatLongInputChangedListener {

    @Inject
    lateinit var presenter: CompassActivityContract.Presenter

    override fun getLayout(): Int = R.layout.activity_compass

    override fun onResume() {
        super.onResume()

        presenter.takeView(this)
    }

    override fun onPause() {
        presenter.dropView()

        super.onPause()
    }

    override fun setViews() {
        (fragment_lat_long_user_input as LatLongUserInput).setLatLongInputChangedListener(this)
    }

    override fun onCorrectLatLongProvided(latitude: Float, longitude: Float) {
        Toast.makeText(this, "navigate", Toast.LENGTH_SHORT).show()
    }

    override fun onWrongInputProvided() {
        Toast.makeText(this, "stop navigating", Toast.LENGTH_SHORT).show()
    }
}

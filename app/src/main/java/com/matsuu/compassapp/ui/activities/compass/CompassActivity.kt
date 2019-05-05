package com.matsuu.compassapp.ui.activities.compass

import com.matsuu.compassapp.R
import com.matsuu.compassapp.ui.activities.AbstractDefaultActivity
import javax.inject.Inject

class CompassActivity : AbstractDefaultActivity(), CompassActivityContract.View {

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
    }
}

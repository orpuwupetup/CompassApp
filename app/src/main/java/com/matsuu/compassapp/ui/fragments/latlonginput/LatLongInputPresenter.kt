package com.matsuu.compassapp.ui.fragments.latlonginput

import javax.inject.Inject

class LatLongInputPresenter @Inject constructor(): LatLongInputFragmentContract.Presenter {

    private var view: LatLongInputFragmentContract.View? = null

    override fun takeView(view: LatLongInputFragmentContract.View) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }
}
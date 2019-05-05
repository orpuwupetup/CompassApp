package com.matsuu.compassapp.ui.fragments.compass

import javax.inject.Inject

class CompassFragmentPresenter @Inject constructor(): CompassFragmentContract.Presenter {

    private var view: CompassFragmentContract.View? = null

    override fun takeView(view: CompassFragmentContract.View) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }
}
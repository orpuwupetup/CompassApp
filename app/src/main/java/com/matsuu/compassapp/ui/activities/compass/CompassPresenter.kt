package com.matsuu.compassapp.ui.activities.compass

class CompassPresenter : CompassActivityContract.Presenter {

    private var view: CompassActivityContract.View? = null

    override fun takeView(view: CompassActivityContract.View) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }
}
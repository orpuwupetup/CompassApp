package com.orpuwupetup.compassapp.ui.main

import javax.inject.Inject

class MainActivityPresenter @Inject constructor(): MainActivityContract.Presenter {

    private var view: MainActivityContract.View? = null

    override fun takeView(view: MainActivityContract.View) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }
}
package com.matsuu.compassapp.ui.fragments.latlonginput

import com.matsuu.compassapp.ui.BasePresenter
import com.matsuu.compassapp.ui.BaseView

interface LatLongInputFragmentContract {

    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter<View>
}
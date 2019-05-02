package com.matsuu.compassapp.ui.activities.compass

import com.matsuu.compassapp.ui.activities.BasePresenter
import com.matsuu.compassapp.ui.activities.BaseView

interface CompassActivityContract {

    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter<View>
}
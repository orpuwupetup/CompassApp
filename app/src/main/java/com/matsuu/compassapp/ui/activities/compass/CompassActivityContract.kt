package com.matsuu.compassapp.ui.activities.compass

import com.matsuu.compassapp.ui.BasePresenter
import com.matsuu.compassapp.ui.BaseView

interface CompassActivityContract {

    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter<View>
}
package com.matsuu.compassapp.ui.fragments.compass

import com.matsuu.compassapp.ui.BasePresenter
import com.matsuu.compassapp.ui.BaseView

interface CompassFragmentContract {

    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter<View>
}
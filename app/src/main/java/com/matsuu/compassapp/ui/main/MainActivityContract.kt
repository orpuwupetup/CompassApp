package com.matsuu.compassapp.ui.main

import com.matsuu.compassapp.ui.BasePresenter
import com.matsuu.compassapp.ui.BaseView

interface MainActivityContract {

    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter<View>
}
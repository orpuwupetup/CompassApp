package com.orpuwupetup.compassapp.ui.main

import com.orpuwupetup.compassapp.ui.BasePresenter
import com.orpuwupetup.compassapp.ui.BaseView

interface MainActivityContract {

    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter<View>
}
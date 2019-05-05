package com.matsuu.compassapp.ui.fragments.compass

import com.matsuu.compassapp.R
import com.matsuu.compassapp.ui.fragments.AbstractFragment
import javax.inject.Inject

class CompassFragment : CompassFragmentContract.View, AbstractFragment() {

    @Inject
    lateinit var presenter: CompassFragmentContract.Presenter

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun getFragmentLayout(): Int = R.layout.fragment_compass
}
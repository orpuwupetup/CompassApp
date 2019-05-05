package com.matsuu.compassapp.ui.fragments.latlonginput

import com.matsuu.compassapp.R
import com.matsuu.compassapp.ui.fragments.AbstractFragment
import kotlinx.android.synthetic.main.fragment_lat_long_input.*
import javax.inject.Inject

class LatLongInputFragment : LatLongInputFragmentContract.View, AbstractFragment() {

    @Inject
    lateinit var presenter: LatLongInputFragmentContract.Presenter

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun getFragmentLayout(): Int = R.layout.fragment_lat_long_input
}
package com.matsuu.compassapp.ui.fragments.latlonginput

// interface for decoupling compass activity from user lat long input fragment
interface LatLongUserInput {
    fun setLatLongInputChangedListener(listener: LatLongInputFragment.LatLongInputChangedListener?)
}
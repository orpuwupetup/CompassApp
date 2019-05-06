package com.matsuu.compassapp.ui.fragments.latlonginput.usableinterface

import com.matsuu.compassapp.ui.fragments.latlonginput.LatLongInputFragment

// interface for decoupling compass activity from user lat long input fragment
interface LatLongUserInput {
    fun setLatLongInputChangedListener(listener: LatLongInputFragment.LatLongInputChangedListener?)
}
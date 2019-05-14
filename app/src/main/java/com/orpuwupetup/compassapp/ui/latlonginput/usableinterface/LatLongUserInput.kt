package com.orpuwupetup.compassapp.ui.latlonginput.usableinterface

import com.orpuwupetup.compassapp.ui.latlonginput.LatLongInputFragment

// interface for decoupling compass activity from user lat long input fragment
interface LatLongUserInput {
    fun setLatLongInputChangedListener(listener: LatLongInputFragment.LatLongInputChangedListener?)
}
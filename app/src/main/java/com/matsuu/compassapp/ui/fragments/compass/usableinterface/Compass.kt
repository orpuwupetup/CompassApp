package com.matsuu.compassapp.ui.fragments.compass.usableinterface

interface Compass {
    fun startNavigation(lat: Float, long: Float)
    fun stopNavigation()
}
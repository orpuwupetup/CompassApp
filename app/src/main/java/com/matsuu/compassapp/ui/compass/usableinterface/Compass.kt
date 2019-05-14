package com.matsuu.compassapp.ui.compass.usableinterface

interface Compass {
    fun startNavigation(lat: Float, long: Float)
    fun stopNavigation()
}
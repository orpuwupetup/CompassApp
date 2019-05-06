package com.matsuu.compassapp.ui.fragments.compass

import android.content.Context
import android.hardware.SensorManager
import com.google.android.gms.location.LocationServices
import com.matsuu.compassapp.data.location.AndroidLocationProvider
import com.matsuu.compassapp.data.sensor.compass.AndroidCompassSensor
import com.matsuu.compassapp.data.sensor.compass.CompassSensor
import com.matsuu.compassapp.ui.activities.compass.CompassActivity
import com.matsuu.compassapp.utils.providers.CompassAnimationProvider
import dagger.Module
import dagger.Provides

@Module
abstract class CompassFragmentModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideCompassSensor(context: Context) : CompassSensor =
            AndroidCompassSensor(context.getSystemService(Context.SENSOR_SERVICE) as SensorManager)

        @Provides
        @JvmStatic
        fun provideCompassAnimationProvider() = CompassAnimationProvider()

        @Provides
        @JvmStatic
        fun provideAndroidLocationProvider(context: CompassActivity) =
            AndroidLocationProvider(LocationServices.getFusedLocationProviderClient(context))
    }
}
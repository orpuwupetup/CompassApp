package com.orpuwupetup.compassapp.ui.compass

import android.content.Context
import android.hardware.SensorManager
import com.google.android.gms.location.LocationServices
import com.orpuwupetup.compassapp.data.location.AndroidLocationProvider
import com.orpuwupetup.compassapp.data.location.usableinterface.LocationProvider
import com.orpuwupetup.compassapp.data.sensor.compass.AndroidCompassSensor
import com.orpuwupetup.compassapp.data.sensor.compass.usableinterface.CompassSensor
import com.orpuwupetup.compassapp.ui.main.MainActivity
import com.orpuwupetup.compassapp.utils.providers.CompassAnimationProvider
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
        fun provideAndroidLocationProvider(context: MainActivity) : LocationProvider =
            AndroidLocationProvider(LocationServices.getFusedLocationProviderClient(context))
    }
}
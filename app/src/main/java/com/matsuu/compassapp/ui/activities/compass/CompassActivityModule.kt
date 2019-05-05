package com.matsuu.compassapp.ui.activities.compass

import android.content.Context
import android.hardware.SensorManager
import com.matsuu.compassapp.di.annotations.ActivityScoped
import com.matsuu.compassapp.di.annotations.FragmentScoped
import com.matsuu.compassapp.sensor.compass.AndroidCompassSensor
import com.matsuu.compassapp.sensor.compass.CompassSensor
import com.matsuu.compassapp.ui.fragments.compass.CompassFragment
import com.matsuu.compassapp.ui.fragments.compass.CompassFragmentContract
import com.matsuu.compassapp.ui.fragments.compass.CompassFragmentModule
import com.matsuu.compassapp.ui.fragments.compass.CompassFragmentPresenter
import com.matsuu.compassapp.ui.fragments.latlonginput.LatLongInputFragment
import com.matsuu.compassapp.ui.fragments.latlonginput.LatLongInputFragmentContract
import com.matsuu.compassapp.ui.fragments.latlonginput.LatLongInputFragmentModule
import com.matsuu.compassapp.ui.fragments.latlonginput.LatLongInputPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class CompassActivityModule {

    @ActivityScoped
    @Binds
    internal abstract fun provideCompassPresenter(presenter: CompassActivityPresenter):
            CompassActivityContract.Presenter

    @ActivityScoped
    @Binds
    internal abstract fun provideLatLongInputPresenter(presenter: LatLongInputPresenter):
            LatLongInputFragmentContract.Presenter

    @ActivityScoped
    @Binds
    internal abstract fun provideCompassFragmentPresenter(presenter: CompassFragmentPresenter):
            CompassFragmentContract.Presenter

    @FragmentScoped
    @ContributesAndroidInjector(modules = [LatLongInputFragmentModule::class])
    internal abstract fun latLongInputFragment(): LatLongInputFragment

    @FragmentScoped
    @ContributesAndroidInjector()
    internal abstract fun compassFragment(): CompassFragment

}
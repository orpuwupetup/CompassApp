package com.matsuu.compassapp.di

import com.matsuu.compassapp.di.annotations.ActivityScoped
import com.matsuu.compassapp.ui.main.MainActivity
import com.matsuu.compassapp.ui.main.MainActivityModule
import com.matsuu.compassapp.ui.compass.CompassFragmentModule
import com.matsuu.compassapp.ui.latlonginput.LatLongInputFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainActivityModule::class, CompassFragmentModule::class, LatLongInputFragmentModule::class])
    internal abstract fun bindsCompassActivity(): MainActivity
}
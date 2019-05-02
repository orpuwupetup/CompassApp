package com.matsuu.compassapp.di

import com.matsuu.compassapp.di.annotations.ActivityScoped
import com.matsuu.compassapp.ui.activities.compass.CompassActivity
import com.matsuu.compassapp.ui.activities.compass.CompassActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [CompassActivityModule::class])
    internal abstract fun bindsCompassActivity(): CompassActivity
}
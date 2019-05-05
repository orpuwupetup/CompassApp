package com.matsuu.compassapp.di

import com.matsuu.compassapp.di.annotations.ActivityScoped
import com.matsuu.compassapp.ui.activities.compass.CompassActivity
import com.matsuu.compassapp.ui.activities.compass.CompassActivityModule
import com.matsuu.compassapp.ui.fragments.compass.CompassFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [CompassActivityModule::class, CompassFragmentModule::class])
    internal abstract fun bindsCompassActivity(): CompassActivity
}
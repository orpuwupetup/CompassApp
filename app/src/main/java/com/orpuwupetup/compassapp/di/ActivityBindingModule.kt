package com.orpuwupetup.compassapp.di

import com.orpuwupetup.compassapp.di.annotations.ActivityScoped
import com.orpuwupetup.compassapp.ui.main.MainActivity
import com.orpuwupetup.compassapp.ui.main.MainActivityModule
import com.orpuwupetup.compassapp.ui.compass.CompassFragmentModule
import com.orpuwupetup.compassapp.ui.latlonginput.LatLongInputFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainActivityModule::class, CompassFragmentModule::class, LatLongInputFragmentModule::class])
    internal abstract fun bindsCompassActivity(): MainActivity
}
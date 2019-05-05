package com.matsuu.compassapp.ui.activities.compass

import com.matsuu.compassapp.di.annotations.ActivityScoped
import com.matsuu.compassapp.di.annotations.FragmentScoped
import com.matsuu.compassapp.ui.fragments.latlonginput.LatLongInputFragment
import com.matsuu.compassapp.ui.fragments.latlonginput.LatLongInputFragmentContract
import com.matsuu.compassapp.ui.fragments.latlonginput.LatLongInputFragmentModule
import com.matsuu.compassapp.ui.fragments.latlonginput.LatLongInputPresenter
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CompassActivityModule {

    @ActivityScoped
    @Binds
    internal abstract fun provideCompassPresenter(presenter: CompassPresenter): CompassActivityContract.Presenter

    @ActivityScoped
    @Binds
    internal abstract fun provideLatLongInputPresenter(presenter: LatLongInputPresenter):
            LatLongInputFragmentContract.Presenter

    @FragmentScoped
    @ContributesAndroidInjector(modules = [LatLongInputFragmentModule::class])
    internal abstract fun latLongInputFragment(): LatLongInputFragment
}
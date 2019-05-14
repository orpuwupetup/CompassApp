package com.orpuwupetup.compassapp.ui.main

import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.SettingsClient
import com.orpuwupetup.compassapp.di.annotations.ActivityScoped
import com.orpuwupetup.compassapp.di.annotations.FragmentScoped
import com.orpuwupetup.compassapp.ui.compass.CompassFragment
import com.orpuwupetup.compassapp.ui.compass.CompassFragmentContract
import com.orpuwupetup.compassapp.ui.compass.CompassFragmentPresenter
import com.orpuwupetup.compassapp.ui.latlonginput.LatLongInputFragment
import com.orpuwupetup.compassapp.ui.latlonginput.LatLongInputFragmentContract
import com.orpuwupetup.compassapp.ui.latlonginput.LatLongInputPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ActivityScoped
    @Binds
    internal abstract fun provideCompassPresenter(presenter: MainActivityPresenter):
            MainActivityContract.Presenter

    @ActivityScoped
    @Binds
    internal abstract fun provideLatLongInputPresenter(presenter: LatLongInputPresenter):
            LatLongInputFragmentContract.Presenter

    @ActivityScoped
    @Binds
    internal abstract fun provideCompassFragmentPresenter(presenter: CompassFragmentPresenter):
            CompassFragmentContract.Presenter

    @FragmentScoped
    @ContributesAndroidInjector()
    internal abstract fun latLongInputFragment(): LatLongInputFragment

    @FragmentScoped
    @ContributesAndroidInjector()
    internal abstract fun compassFragment(): CompassFragment

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideLocationSettingsBuilder(): LocationSettingsRequest.Builder = LocationSettingsRequest.Builder()

        @JvmStatic
        @Provides
        fun provideLocationSettingsClient(context: MainActivity): SettingsClient = LocationServices.getSettingsClient(context)
    }

}
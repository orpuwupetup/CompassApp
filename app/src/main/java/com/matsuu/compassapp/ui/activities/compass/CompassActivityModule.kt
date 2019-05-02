package com.matsuu.compassapp.ui.activities.compass

import com.matsuu.compassapp.di.annotations.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class CompassActivityModule {

    @ActivityScoped
    @Binds
    internal abstract fun provideCompassPresenter(presenter: CompassPresenter): CompassActivityContract.Presenter
}
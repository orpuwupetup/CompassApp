package com.matsuu.compassapp.di

import android.content.Context
import com.matsuu.compassapp.CompassApplication
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    internal abstract fun bindContext(application: CompassApplication): Context
}
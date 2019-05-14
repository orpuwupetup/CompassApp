package com.orpuwupetup.compassapp.di

import android.content.Context
import com.orpuwupetup.compassapp.CompassApplication
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    internal abstract fun bindContext(application: CompassApplication): Context
}
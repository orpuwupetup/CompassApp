package com.orpuwupetup.compassapp.ui.latlonginput

import dagger.Module
import dagger.Provides

@Module
abstract class LatLongInputFragmentModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideErrorStringBuilder() = StringBuilder()
    }
}
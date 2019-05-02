package com.matsuu.compassapp.di

import com.matsuu.compassapp.CompassApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidSupportInjectionModule::class, ActivityBindingModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: CompassApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: CompassApplication)
}
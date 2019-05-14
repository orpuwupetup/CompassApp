package com.orpuwupetup.compassapp.di

import com.orpuwupetup.compassapp.CompassApplication
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
package com.orpuwupetup.compassapp.ui

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

abstract class AbstractDefaultActivity: DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayout())
        setViews()
    }

    // this method is not abstract so that children classes can override it, but don't have to
    open fun setViews() {
        // do nothing
    }

    abstract fun getLayout(): Int
}
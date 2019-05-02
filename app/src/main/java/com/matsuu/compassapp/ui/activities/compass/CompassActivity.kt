package com.matsuu.compassapp.ui.activities.compass

import android.os.Bundle
import android.os.PersistableBundle
import com.matsuu.compassapp.R
import com.matsuu.compassapp.ui.activities.AbstractDefaultActivity

class CompassActivity : AbstractDefaultActivity(), CompassActivityContract.View {

    lateinit var presenter: CompassActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        presenter = CompassPresenter()
    }

    override fun getLayout(): Int = R.layout.activity_compass

    override fun onResume() {
        super.onResume()

        presenter.takeView(this)
    }

    override fun onPause() {
        presenter.dropView()

        super.onPause()
    }
}

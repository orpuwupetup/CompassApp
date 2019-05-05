package com.matsuu.compassapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

abstract class AbstractFragment : DaggerFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(getFragmentLayout(), null)

    abstract fun getFragmentLayout(): Int

    // this method is not abstract so that children classes can override it, but don't have to
    open fun initViews() {
        // do nothing
    }
}
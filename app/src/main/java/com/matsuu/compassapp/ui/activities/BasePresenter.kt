package com.matsuu.compassapp.ui.activities

interface BasePresenter<T> {

    fun takeView(view: T)

    fun dropView()
}
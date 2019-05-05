package com.matsuu.compassapp.ui

interface BasePresenter<T> {

    fun takeView(view: T)

    fun dropView()
}
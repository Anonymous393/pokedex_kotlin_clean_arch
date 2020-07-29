package com.mayandro.cleanarchbaseproject.ui.home

import com.mayandro.cleanarchbaseproject.ui.base.ViewInteractor

interface MainActivityViewInteractor : ViewInteractor{
    fun showAlertDialog(
        title: String,
        message: String
    )
}
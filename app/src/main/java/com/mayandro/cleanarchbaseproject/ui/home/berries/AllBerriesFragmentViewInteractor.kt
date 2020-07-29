package com.mayandro.cleanarchbaseproject.ui.home.berries

import com.mayandro.cleanarchbaseproject.ui.base.ViewInteractor

interface AllBerriesFragmentViewInteractor : ViewInteractor {
    fun showAlertDialog(
        title: String,
        message: String
    )
}
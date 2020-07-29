package com.mayandro.cleanarchbaseproject.ui.home.locations

import com.mayandro.cleanarchbaseproject.ui.base.ViewInteractor

interface AllLocationsFragmentViewInteractor : ViewInteractor {
    fun showAlertDialog(
        title: String,
        message: String
    )
}
package com.mayandro.cleanarchbaseproject.ui.home.types

import com.mayandro.cleanarchbaseproject.ui.base.ViewInteractor

interface AllTypesFragmentViewInteractor : ViewInteractor {
    fun showAlertDialog(
        title: String,
        message: String
    )
}
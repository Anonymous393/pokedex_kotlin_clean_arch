package com.mayandro.cleanarchbaseproject.ui.home.data_setup

import com.mayandro.cleanarchbaseproject.ui.base.ViewInteractor

interface DataSetupFragmentViewInteractor : ViewInteractor {
    fun showAlertDialog(
        title: String,
        message: String
    )
}
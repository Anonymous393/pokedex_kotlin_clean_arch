package com.mayandro.cleanarchbaseproject.ui.home.items

import com.mayandro.cleanarchbaseproject.ui.base.ViewInteractor

interface AllItemsFragmentViewInteractor : ViewInteractor {
    fun showAlertDialog(
        title: String,
        message: String
    )
}
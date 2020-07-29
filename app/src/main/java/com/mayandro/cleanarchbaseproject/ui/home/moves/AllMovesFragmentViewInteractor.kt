package com.mayandro.cleanarchbaseproject.ui.home.moves

import com.mayandro.cleanarchbaseproject.ui.base.ViewInteractor

interface AllMovesFragmentViewInteractor : ViewInteractor {
    fun showAlertDialog(
        title: String,
        message: String
    )
}
package com.mayandro.cleanarchbaseproject.ui.home.pokedex

import com.mayandro.cleanarchbaseproject.ui.base.ViewInteractor

interface PokedexFragmentViewInteractor : ViewInteractor {
    fun showAlertDialog(
        title: String,
        message: String
    )

    fun showLoadingDialog(
        isLoading: Boolean
    )
}
package com.mayandro.cleanarchbaseproject.ui.home.pokedex_item

import com.mayandro.cleanarchbaseproject.ui.base.ViewInteractor

interface PokedexItemFragmentViewInteractor : ViewInteractor {
    fun showAlertDialog(
        title: String,
        message: String
    )
}
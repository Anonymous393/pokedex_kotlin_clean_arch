package com.mayandro.cleanarchbaseproject.ui.home.search_pokemon

import com.mayandro.cleanarchbaseproject.ui.base.ViewInteractor

interface SearchPokemonFragmentViewInteractor : ViewInteractor {
    fun showAlertDialog(
        title: String,
        message: String
    )
}
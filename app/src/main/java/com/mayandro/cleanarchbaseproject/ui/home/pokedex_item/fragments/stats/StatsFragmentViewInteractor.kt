package com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.fragments.stats

import com.mayandro.cleanarchbaseproject.ui.base.ViewInteractor

interface StatsFragmentViewInteractor : ViewInteractor {
    fun showAlertDialog(
        title: String,
        message: String
    )
}
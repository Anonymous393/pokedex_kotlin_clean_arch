package com.mayandro.cleanarchbaseproject.ui.home.pokemon_dashboard

import com.mayandro.cleanarchbaseproject.ui.base.ViewInteractor

interface DashboardFragmentViewInteractor : ViewInteractor {
    fun showAlertDialog(
        title: String,
        message: String
    )
}
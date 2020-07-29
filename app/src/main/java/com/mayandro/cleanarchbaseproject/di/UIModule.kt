package com.mayandro.cleanarchbaseproject.di

import com.mayandro.cleanarchbaseproject.ui.home.MainActivityViewModel
import com.mayandro.cleanarchbaseproject.ui.home.berries.AllBerriesFragmentViewModel
import com.mayandro.cleanarchbaseproject.ui.home.data_setup.DataSetupFragmentViewModel
import com.mayandro.cleanarchbaseproject.ui.home.items.AllItemsFragmentViewModel
import com.mayandro.cleanarchbaseproject.ui.home.locations.AllLocationsFragmentViewModel
import com.mayandro.cleanarchbaseproject.ui.home.moves.AllMovesFragmentViewModel
import com.mayandro.cleanarchbaseproject.ui.home.pokedex.PokedexFragmentViewModel
import com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.PokedexItemFragmentViewModel
import com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.fragments.about.AboutFragmentViewModel
import com.mayandro.cleanarchbaseproject.ui.home.pokemon_dashboard.DashboardFragmentViewModel
import com.mayandro.cleanarchbaseproject.ui.home.search_pokemon.SearchPokemonFragmentViewModel
import com.mayandro.cleanarchbaseproject.ui.home.types.AllTypesFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { MainActivityViewModel() }
    viewModel { PokedexItemFragmentViewModel(repository = get()) }
    viewModel { DashboardFragmentViewModel(repository = get()) }
    viewModel { PokedexFragmentViewModel(repository = get()) }
    viewModel { AboutFragmentViewModel() }
    viewModel { DataSetupFragmentViewModel(repository = get()) }
    viewModel { SearchPokemonFragmentViewModel(repository = get()) }
    viewModel { AllMovesFragmentViewModel(repository = get()) }
    viewModel { AllTypesFragmentViewModel(repository = get()) }
    viewModel { AllItemsFragmentViewModel(repository = get()) }
    viewModel { AllLocationsFragmentViewModel(repository = get()) }
    viewModel { AllBerriesFragmentViewModel(repository = get()) }
}
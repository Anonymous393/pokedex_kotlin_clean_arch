package com.mayandro.cleanarchbaseproject.ui.home.locations

import androidx.lifecycle.asLiveData
import com.mayandro.cleanarchbaseproject.data.repo.PokemonRepository
import com.mayandro.cleanarchbaseproject.ui.base.BaseViewModel

class AllLocationsFragmentViewModel(private val repository: PokemonRepository) : BaseViewModel<AllLocationsFragmentViewInteractor>() {

    fun getAllLocations() = repository.getAllLocationsFromServer().asLiveData()
}

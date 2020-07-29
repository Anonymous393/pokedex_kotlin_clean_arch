package com.mayandro.cleanarchbaseproject.ui.home.berries

import androidx.lifecycle.asLiveData
import com.mayandro.cleanarchbaseproject.data.repo.PokemonRepository
import com.mayandro.cleanarchbaseproject.ui.base.BaseViewModel

class AllBerriesFragmentViewModel(private val repository: PokemonRepository) : BaseViewModel<AllBerriesFragmentViewInteractor>() {

    fun getAllBerries() = repository.getAllBerriesFromServer().asLiveData()
}

package com.mayandro.cleanarchbaseproject.ui.home.types

import androidx.lifecycle.asLiveData
import com.mayandro.cleanarchbaseproject.data.repo.PokemonRepository
import com.mayandro.cleanarchbaseproject.ui.base.BaseViewModel

class AllTypesFragmentViewModel(private val repository: PokemonRepository) : BaseViewModel<AllTypesFragmentViewInteractor>() {

    fun getAllTypes() = repository.getAllTypesFromServer().asLiveData()
}

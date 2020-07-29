package com.mayandro.cleanarchbaseproject.ui.home.items

import androidx.lifecycle.asLiveData
import com.mayandro.cleanarchbaseproject.data.repo.PokemonRepository
import com.mayandro.cleanarchbaseproject.ui.base.BaseViewModel

class AllItemsFragmentViewModel(private val repository: PokemonRepository) : BaseViewModel<AllItemsFragmentViewInteractor>() {

    fun getAllItems() = repository.getAllItemsFromServer().asLiveData()
}

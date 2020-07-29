package com.mayandro.cleanarchbaseproject.ui.home.search_pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mayandro.cleanarchbaseproject.data.repo.PokemonRepository
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.Pokemon
import com.mayandro.cleanarchbaseproject.ui.base.BaseViewModel

class SearchPokemonFragmentViewModel(private val repository: PokemonRepository) : BaseViewModel<SearchPokemonFragmentViewInteractor>() {

    fun getAllPokemon(query: String): LiveData<List<Pokemon>> = repository.searchPokemonByName(query).asLiveData(viewModelScope.coroutineContext)
}

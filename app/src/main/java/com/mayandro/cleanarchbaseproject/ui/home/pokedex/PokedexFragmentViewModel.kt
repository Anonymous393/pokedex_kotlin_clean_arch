package com.mayandro.cleanarchbaseproject.ui.home.pokedex

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mayandro.cleanarchbaseproject.data.repo.PokemonRepository
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.Pokemon
import com.mayandro.cleanarchbaseproject.ui.base.BaseViewModel
import com.mayandro.cleanarchbaseproject.utility.toPokemonList
import com.mayandro.cleanarchbaseproject.utility.toPokemonsList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class PokedexFragmentViewModel(private val repository: PokemonRepository) : BaseViewModel<PokedexFragmentViewInteractor>() {

    init {
        getAllPokemon()
    }

    var listLiveData: MutableLiveData<List<Pokemon>> = MutableLiveData()

    fun getAllPokemon() {
        viewModelScope.launch {
            repository.getPokemonListFromDb().distinctUntilChanged().collect {
                listLiveData.value = it
            }
        }
    }
}
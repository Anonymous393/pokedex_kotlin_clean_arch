package com.mayandro.cleanarchbaseproject.ui.home.moves

import androidx.lifecycle.asLiveData
import com.mayandro.cleanarchbaseproject.data.repo.PokemonRepository
import com.mayandro.cleanarchbaseproject.ui.base.BaseViewModel

class AllMovesFragmentViewModel(private val repository: PokemonRepository) : BaseViewModel<AllMovesFragmentViewInteractor>() {

    fun getAllMoves() = repository.getAllMovesFromServer().asLiveData()
}

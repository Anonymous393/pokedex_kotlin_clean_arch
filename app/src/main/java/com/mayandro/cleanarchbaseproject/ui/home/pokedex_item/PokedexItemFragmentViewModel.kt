package com.mayandro.cleanarchbaseproject.ui.home.pokedex_item

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mayandro.cleanarchbaseproject.data.repo.PokemonRepository
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.Pokemon
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonData
import com.mayandro.cleanarchbaseproject.ui.base.BaseViewModel
import com.mayandro.cleanarchbaseproject.utility.toPokemonData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import java.lang.IndexOutOfBoundsException

class PokedexItemFragmentViewModel(private val repository: PokemonRepository) : BaseViewModel<PokedexItemFragmentViewInteractor>() {

    val mutablePokemonResponseData: MutableLiveData<Pokemon> = MutableLiveData()
    val mutablePokemonDataResponseData: MutableLiveData<PokemonData> = MutableLiveData()
    val statsListLiveData: MutableLiveData<List<PokemonStatsItem>> = MutableLiveData()
    val abilityListLiveData: MutableLiveData<List<String>> = MutableLiveData()
    val movesListLiveData: MutableLiveData<List<String>> = MutableLiveData()

    fun getPokemonForId(id: Int) {
        viewModelScope.launch {
            repository.getPokemonByIdFromDb(id)
                .collect {
                    println("PokedexItemFragmentViewModel.getPokemonForId $id")
                    mutablePokemonResponseData.value = it
                }
        }
    }

    private fun fetchPokemonDataFromServer(orderId: Int, nDex: Int) {
        viewModelScope.launch {
            repository.getPokemonDataByNumberFromServer(nDex)
                .transform {
                    if(it.isEmpty()) {
                        emit(null)
                    } else {
                        repository.savePokemonDataToDb(it.first().toPokemonData(orderId))
                        emit(it.first().toPokemonData(orderId))
                    }
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    it?.let {
                        mutablePokemonDataResponseData.value = it
                    }
                }
        }

    }
    fun fetchPokemonDataFromDB(orderId: Int, nDex: Int) {
        viewModelScope.launch {
            repository.getPokemonDataByIdFromDb(orderId)
                .flowOn(Dispatchers.IO)
                .collect { data ->
                    data?.let {
                        mutablePokemonDataResponseData.value = it
                    } ?: kotlin.run {
                        fetchPokemonDataFromServer(orderId, nDex)
                    }
                }
        }
    }

    fun getPokemonMovesForId(id: Int) {
        viewModelScope.launch {
            repository.getPokemonMovesFromDb(id)
                .collect { value ->
                    value?.let {
                        abilityListLiveData.value = it.hidden
                        movesListLiveData.value = it.moves
                    }
                }
        }
    }

    fun getPokemonStats(id: Int){
        val list = mutableListOf<PokemonStatsItem>()
        viewModelScope.launch {
            repository.getPokemonStatsByIdFromDb(id)
                .flowOn(Dispatchers.IO)
                .collect { data ->
                    data?.let {
                        val attack = PokemonStatsItem("Attack", it.attack)
                        val defence = PokemonStatsItem("Defence", it.defense)
                        val hp = PokemonStatsItem("HP", it.hp)
                        val sDefence = PokemonStatsItem("Special Attack", it.specialAttack)
                        val sAttack = PokemonStatsItem("Special Defence", it.specialDefense)
                        val speed = PokemonStatsItem("Speed", it.speed)

                        list.add(hp)
                        list.add(attack)
                        list.add(defence)
                        list.add(sAttack)
                        list.add(sDefence)
                        list.add(speed)

                        statsListLiveData.value = list
                    }
                }
        }
    }
}


data class PokemonStatsItem(
    val name: String,
    val progress: Int
)
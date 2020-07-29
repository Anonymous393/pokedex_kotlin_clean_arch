package com.mayandro.cleanarchbaseproject.data.source

import com.mayandro.cleanarchbaseproject.data.model.apimodel.*
import com.mayandro.cleanarchbaseproject.data.source.local.LocalPokemonDataSource
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.Pokemon
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonData
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonMoves
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonStats
import com.mayandro.cleanarchbaseproject.data.source.network.NetworkPokemonDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class PokemonDataSourceFactory(
    private val localPokemonDataSource: LocalPokemonDataSource,
    private val networkPokemonDataSource: NetworkPokemonDataSource
) {

    @ExperimentalCoroutinesApi
    open fun getPokemonByNameFromServer(name: String): Flow<PokemonResponse> {
        return networkPokemonDataSource.getPokemonByName(name)
    }

    @ExperimentalCoroutinesApi
    open fun getPokemonByIdFromServer(id: Int): Flow<PokemonResponse> {
        return networkPokemonDataSource.getPokemonByNumber(id)
    }

    @ExperimentalCoroutinesApi
    fun getPokemonDataByNumber(number: Int): Flow<List<PokemonResponseModel>> {
        return networkPokemonDataSource.getPokemonDataByNumber(number = number)
    }

    @ExperimentalCoroutinesApi
    open fun getPokedexEntiresFromServer(): Flow<List<PokedexItem>> {
        return networkPokemonDataSource.getPokedexEntries()
    }

    @ExperimentalCoroutinesApi
    fun getAllMoves(): Flow<List<PokemonMovesResponseItem>> {
        return networkPokemonDataSource.getAllMoves()
    }

    @ExperimentalCoroutinesApi
    fun getAllTypes(): Flow<List<PokemonTypesResponseItem>> {
        return networkPokemonDataSource.getAllTypes()
    }

    @ExperimentalCoroutinesApi
    fun getAllBerries(): Flow<List<PokemonBerryResponseItem>> {
        return networkPokemonDataSource.getAllBerries()
    }

    @ExperimentalCoroutinesApi
    fun getAllItems(): Flow<List<PokemonItemResponseItem>> {
        return networkPokemonDataSource.getAllItems()
    }

    @ExperimentalCoroutinesApi
    fun getAllLocations(): Flow<List<PokemonLocationResponseItem>> {
        return networkPokemonDataSource.getAllLocations()
    }



    fun getPokemonListFromDb(): Flow<List<Pokemon>> {
        return localPokemonDataSource.getPokemonList()
    }

    fun getPokemonListByQueryFromDb(query: String): Flow<List<Pokemon>> {
        return localPokemonDataSource.searchPokemonByQuery(query)
    }

    fun getLastSavedPokemonFromDb(): Flow<Pokemon?> {
        return localPokemonDataSource.getLastSavedPokemon()
    }

    fun getPokemonByNameFromDb(name: String): Flow<Pokemon> {
        return localPokemonDataSource.getPokemonByName(name)
    }

    fun getPokemonByIdFromDb(id: Int): Flow<Pokemon> {
        return localPokemonDataSource.getPokemonById(id)
    }

    fun clearPokemonList(): Flow<Void> {
        return localPokemonDataSource.clearPokemonList()
    }

    fun savePokemonList(list: List<Pokemon>): Flow<Boolean> {
        return localPokemonDataSource.savePokemonList(list)
    }

    fun savePokemon(pokemonResponse: Pokemon) {
        return localPokemonDataSource.savePokemon(pokemonResponse)
    }

    fun savePokemonData(pokemonData: PokemonData) {
        return localPokemonDataSource.savePokemonData(pokemonData)
    }

    fun savePokemonStats(pokemonData: PokemonStats) {
        return localPokemonDataSource.savePokemonStats(pokemonData)
    }

    fun savePokemonMoves(pokemonData: PokemonMoves) {
        return localPokemonDataSource.savePokemonMoves(pokemonData)
    }

    fun getPokemonDataByIdFromDb(id: Int): Flow<PokemonData?> {
        return localPokemonDataSource.getPokemonDataById(id)
    }

    fun getPokemonStatsByIdFromDb(id: Int): Flow<PokemonStats?> {
        return localPokemonDataSource.getPokemonStatsById(id)
    }

    fun getPokemonMovesByIdFromDb(id: Int): Flow<PokemonMoves?> {
        return localPokemonDataSource.getPokemonMovesById(id)
    }
}
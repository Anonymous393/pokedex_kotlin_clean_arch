package com.mayandro.cleanarchbaseproject.data.repo

import com.mayandro.cleanarchbaseproject.data.model.apimodel.*
import com.mayandro.cleanarchbaseproject.data.source.PokemonDataSourceFactory
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.Pokemon
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonData
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonMoves
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonStats
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class PokemonRepository(private val pokemonDataSourceFactory: PokemonDataSourceFactory) {


    fun getPokemonByNameFromServer(name: String): Flow<PokemonResponse> {
        return pokemonDataSourceFactory.getPokemonByNameFromServer(name)
    }

    fun getPokemonByIdFromServer(id: Int): Flow<PokemonResponse> {
        return pokemonDataSourceFactory.getPokemonByIdFromServer(id)
    }

    fun getPokemonDataByNumberFromServer(number: Int): Flow<List<PokemonResponseModel>> {
        return pokemonDataSourceFactory.getPokemonDataByNumber(number)
    }

    fun getPokedexEntiresFromServer(): Flow<List<PokedexItem>> {
        return pokemonDataSourceFactory.getPokedexEntiresFromServer()
    }

    @ExperimentalCoroutinesApi
    fun getAllMovesFromServer(): Flow<List<PokemonMovesResponseItem>> {
        return pokemonDataSourceFactory.getAllMoves()
    }

    @ExperimentalCoroutinesApi
    fun getAllTypesFromServer(): Flow<List<PokemonTypesResponseItem>> {
        return pokemonDataSourceFactory.getAllTypes()
    }

    @ExperimentalCoroutinesApi
    fun getAllBerriesFromServer(): Flow<List<PokemonBerryResponseItem>> {
        return pokemonDataSourceFactory.getAllBerries()
    }

    @ExperimentalCoroutinesApi
    fun getAllItemsFromServer(): Flow<List<PokemonItemResponseItem>> {
        return pokemonDataSourceFactory.getAllItems()
    }

    @ExperimentalCoroutinesApi
    fun getAllLocationsFromServer(): Flow<List<PokemonLocationResponseItem>> {
        return pokemonDataSourceFactory.getAllLocations()
    }



    fun getPokemonListFromDb(): Flow<List<Pokemon>> {
        return pokemonDataSourceFactory.getPokemonListFromDb()
    }

    fun searchPokemonByName(query: String): Flow<List<Pokemon>> {
        return pokemonDataSourceFactory.getPokemonListByQueryFromDb(query)
    }

    fun getPokemonByNameFromDb(name: String): Flow<Pokemon> {
        return pokemonDataSourceFactory.getPokemonByNameFromDb(name)
    }

    fun getLastSavedPokemonFromDb(): Flow<Pokemon?> {
        return pokemonDataSourceFactory.getLastSavedPokemonFromDb()
    }

    fun getPokemonByIdFromDb(id: Int): Flow<Pokemon> {
        return pokemonDataSourceFactory.getPokemonByIdFromDb(id)
    }

    fun clearPokemonDataFromDb(): Flow<Void> {
       return pokemonDataSourceFactory.clearPokemonList()
    }

    fun savePokemonListToDb(list: List<Pokemon>): Flow<Boolean> {
        return pokemonDataSourceFactory.savePokemonList(list)
    }

    fun savePokemonToDb(item: Pokemon) {
        return pokemonDataSourceFactory.savePokemon(item)
    }

    fun savePokemonDataToDb(item: PokemonData) {
        return pokemonDataSourceFactory.savePokemonData(item)
    }

    fun getPokemonDataByIdFromDb(id: Int): Flow<PokemonData?> {
        return pokemonDataSourceFactory.getPokemonDataByIdFromDb(id)
    }

    fun savePokemonStatsToDb(item: PokemonStats) {
        return pokemonDataSourceFactory.savePokemonStats(item)
    }

    fun getPokemonStatsByIdFromDb(id: Int): Flow<PokemonStats?> {
        return pokemonDataSourceFactory.getPokemonStatsByIdFromDb(id)
    }

    fun savePokemonMovesToDb(item: PokemonMoves) {
        return pokemonDataSourceFactory.savePokemonMoves(item)
    }

    fun getPokemonMovesFromDb(id: Int): Flow<PokemonMoves?> {
        return pokemonDataSourceFactory.getPokemonMovesByIdFromDb(id)
    }
}
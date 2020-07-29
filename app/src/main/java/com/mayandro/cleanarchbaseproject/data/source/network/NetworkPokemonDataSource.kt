package com.mayandro.cleanarchbaseproject.data.source.network

import com.mayandro.cleanarchbaseproject.data.model.apimodel.*
import com.mayandro.cleanarchbaseproject.data.source.network.retrofit.ServiceApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkPokemonDataSource(private val apiService: ServiceApi) {
    fun getPokemonByName(name: String): Flow<PokemonResponse> {
        return flow{
            emit(apiService.getPokemonByName(name))
        }
    }

    fun getPokemonByNumber(number: Int): Flow<PokemonResponse> {
        return flow{
            emit(apiService.getPokemonByIdFromPokeApi(number))
        }
    }

    fun getPokemonDataByNumber(number: Int): Flow<List<PokemonResponseModel>> {
        return flow{
            emit(apiService.getPokemonByIdFromGlitch("https://pokeapi.glitch.me/v1/pokemon/$number"))
        }
    }

    fun getPokedexEntries(): Flow<List<PokedexItem>> {
        return flow{
            emit(apiService.getPokedexEntries())
        }
    }

    fun getAllMoves(): Flow<List<PokemonMovesResponseItem>> {
        return flow{
            emit(apiService.getAllMoves().results)
        }
    }

    fun getAllTypes(): Flow<List<PokemonTypesResponseItem>> {
        return flow{
            emit(apiService.getAllTypes().results)
        }
    }

    fun getAllBerries(): Flow<List<PokemonBerryResponseItem>> {
        return flow{
            emit(apiService.getAllBerries().results)
        }
    }

    fun getAllItems(): Flow<List<PokemonItemResponseItem>> {
        return flow{
            emit(apiService.getAllItems().results)
        }
    }

    fun getAllLocations(): Flow<List<PokemonLocationResponseItem>> {
        return flow{
            emit(apiService.getAllLocations().results)
        }
    }
}
package com.mayandro.cleanarchbaseproject.data.source.network.retrofit

import com.mayandro.cleanarchbaseproject.data.model.apimodel.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ServiceApi {

    @GET
    suspend fun getPokemonByIdFromGlitch(
        @Url url: String
    ) : List<PokemonResponseModel>

    @GET("pokemon/{id}")
    suspend fun getPokemonByIdFromPokeApi(
        @Path("id") id: Int
    ) : PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ) : PokemonResponse

    @GET
    suspend fun getPokedexEntries(@Url url: String = "https://pokedexvuejs.herokuapp.com/pokedexdb") : List<PokedexItem>

    @GET("move")
    suspend fun getAllMoves() : PokemonMovesResponse

    @GET("type")
    suspend fun getAllTypes() : PokemonTypesResponse

    @GET("berry")
    suspend fun getAllBerries() : PokemonBerryResponse

    @GET("item")
    suspend fun getAllItems() : PokemonItemResponse

    @GET("location")
    suspend fun getAllLocations() : PokemonLocationResponse
}
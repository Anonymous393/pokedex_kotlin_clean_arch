package com.mayandro.cleanarchbaseproject.data.source.local.room.dao

import androidx.room.*
import com.mayandro.cleanarchbaseproject.data.source.local.room.DbConstants
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemon: Pokemon)

    @Update
    fun updatePokemon(pokemon: Pokemon)

    @Query(DbConstants.QUERY_ALL_POKEMON)
    fun getAllPokemon(): Flow<List<Pokemon>>

    @Query(DbConstants.QUERY_POKEMON_BY_NAME)
    fun getPokemonByName(name: String): Flow<Pokemon>

    @Query(DbConstants.QUERY_POKEMON_BY_ID)
    fun getPokemonByNumber(orderID: Int): Flow<Pokemon>

    @Query(DbConstants.QUERY_LAST_POKEMON_ID)
    fun getLastSavedPokemon(): Flow<Pokemon?>

    @Query(DbConstants.DELETE_ALL_POKEMON)
    fun clearDatabase()

    @Query(DbConstants.SEARCH_QUERY_POKEMON)
    fun getPokemonBySearchQuery(query: String): Flow<List<Pokemon>>
}
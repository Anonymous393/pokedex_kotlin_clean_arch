package com.mayandro.cleanarchbaseproject.data.source.local.room.dao

import androidx.room.*
import com.mayandro.cleanarchbaseproject.data.source.local.room.DbConstants
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonData
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDataDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonData(pokemon: PokemonData)

    @Update
    fun updatePokemonData(pokemon: PokemonData)

    @Query(DbConstants.QUERY_POKEMON_DATA_BY_ID)
    fun getPokemonDataById(orderID: Int): Flow<PokemonData?>

    @Query(DbConstants.DELETE_ALL_POKEMON_DATA)
    fun clearDatabase()
}
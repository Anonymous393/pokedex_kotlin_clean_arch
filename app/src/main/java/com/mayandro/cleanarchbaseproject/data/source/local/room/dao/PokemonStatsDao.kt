package com.mayandro.cleanarchbaseproject.data.source.local.room.dao

import androidx.room.*
import com.mayandro.cleanarchbaseproject.data.source.local.room.DbConstants
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonStats
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonStatsDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonStats(pokemon: PokemonStats)

    @Update
    fun updatePokemonStats(pokemon: PokemonStats)

    @Query(DbConstants.QUERY_POKEMON_STATS_BY_ID)
    fun getPokemonStatsById(orderID: Int): Flow<PokemonStats>

    @Query(DbConstants.DELETE_ALL_POKEMON_STATS)
    fun clearDatabase()
}
package com.mayandro.cleanarchbaseproject.data.source.local.room.dao

import androidx.room.*
import com.mayandro.cleanarchbaseproject.data.source.local.room.DbConstants
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonMoves
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonMoveDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonMoves(pokemon: PokemonMoves)

    @Update
    fun updatePokemonMoves(pokemon: PokemonMoves)

    @Query(DbConstants.QUERY_POKEMON_MOVES_BY_ID)
    fun getPokemonMovesById(orderID: Int): Flow<PokemonMoves>

    @Query(DbConstants.DELETE_ALL_POKEMON_MOVES)
    fun clearDatabase()
}
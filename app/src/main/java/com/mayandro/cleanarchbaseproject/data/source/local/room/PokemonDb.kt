package com.mayandro.cleanarchbaseproject.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mayandro.cleanarchbaseproject.data.source.local.room.dao.PokemonDao
import com.mayandro.cleanarchbaseproject.data.source.local.room.dao.PokemonDataDao
import com.mayandro.cleanarchbaseproject.data.source.local.room.dao.PokemonMoveDao
import com.mayandro.cleanarchbaseproject.data.source.local.room.dao.PokemonStatsDao
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.*

@Database(entities = [Pokemon::class, PokemonData::class, PokemonMoves::class, PokemonStats::class], version = 2, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class PokemonDb: RoomDatabase(){
    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonMovesDao(): PokemonMoveDao
    abstract fun pokemonDataDao(): PokemonDataDao
    abstract fun pokemonStatsDao(): PokemonStatsDao
}
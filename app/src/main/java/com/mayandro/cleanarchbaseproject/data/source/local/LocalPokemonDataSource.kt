package com.mayandro.cleanarchbaseproject.data.source.local

import com.mayandro.cleanarchbaseproject.data.source.local.prefs.PreferencesHelper
import com.mayandro.cleanarchbaseproject.data.source.local.room.dao.PokemonDao
import com.mayandro.cleanarchbaseproject.data.source.local.room.dao.PokemonDataDao
import com.mayandro.cleanarchbaseproject.data.source.local.room.dao.PokemonMoveDao
import com.mayandro.cleanarchbaseproject.data.source.local.room.dao.PokemonStatsDao
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.Pokemon
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonData
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonMoves
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonStats
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform

class LocalPokemonDataSource(
    private val pokemonDao: PokemonDao,
    private val pokemonMoveDao: PokemonMoveDao,
    private val pokemonStatsDao: PokemonStatsDao,
    private val pokemonDataDao: PokemonDataDao,
    private val preferencesHelper: PreferencesHelper
) {

    companion object {
        const val EXPIRATION_TIME = (60 * 10 * 1000).toLong()
    }

    fun getPokemonList(): Flow<List<Pokemon>> {
        return pokemonDao.getAllPokemon()
    }

    fun searchPokemonByQuery(query: String): Flow<List<Pokemon>> {
        return pokemonDao.getPokemonBySearchQuery(query)
    }

    fun getLastSavedPokemon(): Flow<Pokemon?> {
        return pokemonDao.getLastSavedPokemon()
    }


    fun getPokemonByName(name: String): Flow<Pokemon> {
        return pokemonDao.getPokemonByName(name)
    }

    fun getPokemonById(id: Int): Flow<Pokemon> {
        return pokemonDao.getPokemonByNumber(id)
    }

    fun savePokemonList(list: List<Pokemon>): Flow<Boolean> {
        return flow {
            list.forEach{
                pokemonDao.insertPokemon(it)
            }
            preferencesHelper.lastCacheTime = System.currentTimeMillis()
            emit(true)
        }
    }

    fun savePokemon(item: Pokemon) {
        pokemonDao.insertPokemon(item)
        preferencesHelper.lastCacheTime = System.currentTimeMillis()
    }

    fun savePokemonData(data: PokemonData) {
        return pokemonDataDao.insertPokemonData(data)
    }

    fun savePokemonStats(data: PokemonStats)  {
        return pokemonStatsDao.insertPokemonStats(data)
    }

    fun savePokemonMoves(data: PokemonMoves)  {
        return pokemonMoveDao.insertPokemonMoves(data)
    }

    fun clearPokemonList(): Flow<Void> {
        return flow{
            pokemonDao.clearDatabase()
        }
    }

    /**
     * Check whether there are instances of data stored in the cache.
     */
    @ExperimentalCoroutinesApi
    fun isCached(): Flow<Boolean> = pokemonDao.getAllPokemon()
        .transform { list ->
            emit(list.isNotEmpty())
        }

    /**
     * Set a point in time at when the cache was last updated.
     */
    fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time.
     */
    fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME

    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

    fun getPokemonDataById(id: Int): Flow<PokemonData?> {
        return pokemonDataDao.getPokemonDataById(id)
    }

    fun getPokemonStatsById(id: Int): Flow<PokemonStats?> {
        return pokemonStatsDao.getPokemonStatsById(id)
    }

    fun getPokemonMovesById(id: Int): Flow<PokemonMoves?> {
        return pokemonMoveDao.getPokemonMovesById(id)
    }
}
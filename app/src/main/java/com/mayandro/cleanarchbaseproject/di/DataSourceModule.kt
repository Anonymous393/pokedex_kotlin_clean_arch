package com.mayandro.cleanarchbaseproject.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.mayandro.cleanarchbaseproject.data.repo.PokemonRepository
import com.mayandro.cleanarchbaseproject.data.source.PokemonDataSourceFactory
import com.mayandro.cleanarchbaseproject.data.source.local.LocalPokemonDataSource
import com.mayandro.cleanarchbaseproject.data.source.local.prefs.PreferencesHelper
import com.mayandro.cleanarchbaseproject.data.source.local.room.DbConstants.ROOM_DB_STRING
import com.mayandro.cleanarchbaseproject.data.source.local.room.PokemonDb
import com.mayandro.cleanarchbaseproject.data.source.local.room.dao.PokemonDao
import com.mayandro.cleanarchbaseproject.data.source.local.room.dao.PokemonDataDao
import com.mayandro.cleanarchbaseproject.data.source.local.room.dao.PokemonMoveDao
import com.mayandro.cleanarchbaseproject.data.source.local.room.dao.PokemonStatsDao
import com.mayandro.cleanarchbaseproject.data.source.network.NetworkPokemonDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module(override=true) {
    single { provideSharedPreference(androidApplication()) }
    single { PreferencesHelper(sharedPreferences = get()) }

    single { provideDb(androidApplication()) }
    single { providePokemonDao(get()) }
    single { providePokemonMovesDao(get()) }
    single { providePokemonDataDao(get()) }
    single { providePokemonStatsDao(get()) }

    single { NetworkPokemonDataSource(apiService = get()) }
    single { LocalPokemonDataSource(
        pokemonDao = get(),
        pokemonDataDao = get(),
        pokemonMoveDao = get(),
        pokemonStatsDao = get(),
        preferencesHelper = get())
    }

    single { PokemonDataSourceFactory(localPokemonDataSource = get(), networkPokemonDataSource = get()) }

    single { PokemonRepository(pokemonDataSourceFactory = get()) }

}

fun provideSharedPreference(context: Context): SharedPreferences = context.getSharedPreferences("org.buffer.android.boilerplate.preferences", Context.MODE_PRIVATE)

fun provideDb(application: Application): PokemonDb {
    return Room.databaseBuilder<PokemonDb>(
        application,
        PokemonDb::class.java,
        ROOM_DB_STRING
    ).build()
}

fun providePokemonDao(db: PokemonDb): PokemonDao {
    return db.pokemonDao()
}

fun providePokemonMovesDao(db: PokemonDb): PokemonMoveDao {
    return db.pokemonMovesDao()
}

fun providePokemonDataDao(db: PokemonDb): PokemonDataDao {
    return db.pokemonDataDao()
}

fun providePokemonStatsDao(db: PokemonDb): PokemonStatsDao {
    return db.pokemonStatsDao()
}
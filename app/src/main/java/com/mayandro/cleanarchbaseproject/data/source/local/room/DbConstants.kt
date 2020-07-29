package com.mayandro.cleanarchbaseproject.data.source.local.room

object DbConstants {

    const val ROOM_DB_STRING = "pokemon.db"

    const val POKEMON_TABLE = "pokemon_table"

    const val POKEMON_MOVES_TABLE = "pokemon_moves_table"

    const val POKEMON_DATA_TABLE = "pokemon_data_table"

    const val POKEMON_STATS_TABLE = "pokemon_stats_table"


    // POKEMON TABLE
    const val QUERY_ALL_POKEMON = "SELECT * FROM $POKEMON_TABLE"

    const val SEARCH_QUERY_POKEMON = "SELECT * FROM $POKEMON_TABLE WHERE name LIKE '%' || :query || '%'"

    const val QUERY_POKEMON_BY_ID = "SELECT * FROM $POKEMON_TABLE WHERE orderID = :orderID"

    const val QUERY_POKEMON_BY_NAME = "SELECT * FROM $POKEMON_TABLE WHERE name = :name"

    const val QUERY_LAST_POKEMON_ID = "SELECT * FROM $POKEMON_TABLE ORDER BY number DESC LIMIT 1"

    const val DELETE_ALL_POKEMON = "DELETE FROM $POKEMON_TABLE"

    // POKEMON MOVES TABLE
    const val QUERY_POKEMON_MOVES_BY_ID = "SELECT * FROM $POKEMON_MOVES_TABLE WHERE orderID = :orderID"

    const val DELETE_ALL_POKEMON_MOVES = "DELETE FROM $POKEMON_MOVES_TABLE"

    // POKEMON DATA TABLE
    const val QUERY_POKEMON_DATA_BY_ID = "SELECT * FROM $POKEMON_DATA_TABLE WHERE orderID = :orderID"

    const val DELETE_ALL_POKEMON_DATA = "DELETE FROM $POKEMON_DATA_TABLE"

    // POKEMON STATS TABLE
    const val QUERY_POKEMON_STATS_BY_ID = "SELECT * FROM $POKEMON_STATS_TABLE WHERE orderID = :orderID"

    const val DELETE_ALL_POKEMON_STATS = "DELETE FROM $POKEMON_STATS_TABLE"

}
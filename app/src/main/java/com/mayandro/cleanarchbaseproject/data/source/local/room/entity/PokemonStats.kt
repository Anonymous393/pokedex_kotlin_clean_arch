package com.mayandro.cleanarchbaseproject.data.source.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mayandro.cleanarchbaseproject.data.source.local.room.DbConstants

@Entity(tableName = DbConstants.POKEMON_STATS_TABLE)
data class PokemonStats(
    @PrimaryKey
    @ColumnInfo(name = "orderID")
    @SerializedName("orderID")
    val orderID : Int,

    @ColumnInfo(name = "number")
    @SerializedName("number")
    val number : Int,

    @ColumnInfo(name = "hp")
    @SerializedName("hp")
    val hp : Int,

    @ColumnInfo(name = "attack")
    @SerializedName("attack")
    val attack : Int,

    @ColumnInfo(name = "defense")
    @SerializedName("defense")
    val defense : Int,

    @ColumnInfo(name = "special-attack")
    @SerializedName("special-attack")
    val specialAttack : Int,

    @ColumnInfo(name = "special-defense")
    @SerializedName("special-defense")
    val specialDefense : Int,

    @ColumnInfo(name = "speed")
    @SerializedName("speed")
    val speed : Int
)
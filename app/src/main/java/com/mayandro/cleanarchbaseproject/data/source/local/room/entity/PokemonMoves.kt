package com.mayandro.cleanarchbaseproject.data.source.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mayandro.cleanarchbaseproject.data.source.local.room.DbConstants

@Entity(tableName = DbConstants.POKEMON_MOVES_TABLE)
data class PokemonMoves(
    @PrimaryKey
    @ColumnInfo(name = "orderID")
    @SerializedName("orderID")
    val orderID : Int,

    @ColumnInfo(name = "number")
    @SerializedName("number")
    val number : Int,

    @ColumnInfo(name = "normal_ability")
    @SerializedName("normal_ability")
    val normal : List<String>,

    @ColumnInfo(name = "hidden_ability")
    @SerializedName("hidden_ability")
    val hidden : List<String>,

    @ColumnInfo(name = "moves")
    @SerializedName("moves")
    val moves : List<String>
)
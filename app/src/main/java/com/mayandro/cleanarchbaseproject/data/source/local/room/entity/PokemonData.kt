package com.mayandro.cleanarchbaseproject.data.source.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mayandro.cleanarchbaseproject.data.source.local.room.DbConstants

@Entity(tableName = DbConstants.POKEMON_DATA_TABLE)
data class PokemonData(
    @PrimaryKey
    @ColumnInfo(name = "orderID")
    @SerializedName("orderID")
    val orderID : Int,

    @ColumnInfo(name = "number")
    @SerializedName("number")
    val number : Int,

    @ColumnInfo(name = "species")
    @SerializedName("species")
    val species : String,

    @ColumnInfo(name = "eggGroups")
    @SerializedName("eggGroups")
    val eggGroups : List<String>,

    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    val gender : List<String>,

    @ColumnInfo(name = "height")
    @SerializedName("height")
    val height : String,

    @ColumnInfo(name = "weight")
    @SerializedName("weight")
    val weight : String,

    @ColumnInfo(name = "family")
    @SerializedName("family")
    val family : Family,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description : String
)

data class Family (
    @SerializedName("id")
    val id : Int,

    @SerializedName("evolutionStage")
    val evolutionStage : Int,

    @SerializedName("evolutionLine")
    val evolutionLine : List<String>
)
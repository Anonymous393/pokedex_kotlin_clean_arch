package com.mayandro.cleanarchbaseproject.data.source.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mayandro.cleanarchbaseproject.data.source.local.room.DbConstants

@Entity(tableName = DbConstants.POKEMON_TABLE)
data class Pokemon(
    @PrimaryKey
    @ColumnInfo(name = "orderID")
    @SerializedName("orderID")
    val orderID : Int,

    @ColumnInfo(name = "number")
    @SerializedName("number")
    val number : Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name : String,

    @ColumnInfo(name = "types")
    @SerializedName("types")
    val types : List<String>,

    @ColumnInfo(name = "starter")
    @SerializedName("starter")
    val starter : Boolean,

    @ColumnInfo(name = "legendary")
    @SerializedName("legendary")
    val legendary : Boolean,

    @ColumnInfo(name = "mythical")
    @SerializedName("mythical")
    val mythical : Boolean,

    @ColumnInfo(name = "ultraBeast")
    @SerializedName("ultraBeast")
    val ultraBeast : Boolean,

    @ColumnInfo(name = "mega")
    @SerializedName("mega")
    val mega : Boolean,

    @ColumnInfo(name = "gen")
    @SerializedName("gen")
    val gen : Int,

    @ColumnInfo(name = "sprite")
    @SerializedName("sprite")
    val sprite : String
)
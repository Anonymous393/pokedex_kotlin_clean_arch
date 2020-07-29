package com.mayandro.cleanarchbaseproject.data.model.apimodel

import com.google.gson.annotations.SerializedName

data class PokedexItem (
    @SerializedName("_id") val id : String,
    @SerializedName("orderID") val orderID : Int,
    @SerializedName("nDex") val nDex : Int,
    @SerializedName("name") val name : String,
    @SerializedName("type1") val type1 : String,
    @SerializedName("type2") val type2 : String,
    @SerializedName("ability1") val ability1 : String,
    @SerializedName("ability2") val ability2 : String,
    @SerializedName("hiddenability") val hiddenability : String,
    @SerializedName("hp") val hp : Int,
    @SerializedName("atk") val atk : Int,
    @SerializedName("def") val def : Int,
    @SerializedName("spatk") val spatk : Int,
    @SerializedName("spdef") val spdef : Int,
    @SerializedName("spe") val spe : Int,
    @SerializedName("image") val image : String,
    @SerializedName("tier") val tier : String,
    @SerializedName("note") val note : String

)

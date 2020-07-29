package com.mayandro.cleanarchbaseproject.data.model.apimodel

import com.google.gson.annotations.SerializedName
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.Family

data class PokemonResponseModel (
    @SerializedName("number") val number : Int,
    @SerializedName("name") val name : String,
    @SerializedName("species") val species : String,
    @SerializedName("types") val types : List<String>,
    @SerializedName("abilities") val abilities : AbilitiesList,
    @SerializedName("eggGroups") val eggGroups : List<String>,
    @SerializedName("gender") val gender : List<String>,
    @SerializedName("height") val height : String,
    @SerializedName("weight") val weight : String,
    @SerializedName("family") val family : Family,
    @SerializedName("starter") val starter : Boolean,
    @SerializedName("legendary") val legendary : Boolean,
    @SerializedName("mythical") val mythical : Boolean,
    @SerializedName("ultraBeast") val ultraBeast : Boolean,
    @SerializedName("mega") val mega : Boolean,
    @SerializedName("gen") val gen : Int,
    @SerializedName("sprite") val sprite : String,
    @SerializedName("description") val description : String
)

data class AbilitiesList (
    @SerializedName("normal") val normal : List<String>,
    @SerializedName("hidden") val hidden : List<String>
)
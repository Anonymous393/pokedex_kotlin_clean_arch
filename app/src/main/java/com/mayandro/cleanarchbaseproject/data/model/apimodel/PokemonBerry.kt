package com.mayandro.cleanarchbaseproject.data.model.apimodel

import com.google.gson.annotations.SerializedName

data class PokemonBerryResponse (
    @SerializedName("results") val results : List<PokemonBerryResponseItem>
)

data class PokemonBerryResponseItem (
    @SerializedName("name") val name : String
)

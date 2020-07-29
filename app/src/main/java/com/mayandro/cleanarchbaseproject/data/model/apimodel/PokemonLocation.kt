package com.mayandro.cleanarchbaseproject.data.model.apimodel

import com.google.gson.annotations.SerializedName

data class PokemonLocationResponse (
    @SerializedName("results") val results : List<PokemonLocationResponseItem>
)

data class PokemonLocationResponseItem (
    @SerializedName("name") val name : String
)

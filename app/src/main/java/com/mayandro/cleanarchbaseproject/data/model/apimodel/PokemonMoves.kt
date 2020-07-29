package com.mayandro.cleanarchbaseproject.data.model.apimodel

import com.google.gson.annotations.SerializedName

data class PokemonMovesResponse (
    @SerializedName("results") val results : List<PokemonMovesResponseItem>
)

data class PokemonMovesResponseItem (
    @SerializedName("name") val name : String
)

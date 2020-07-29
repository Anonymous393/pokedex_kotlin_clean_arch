package com.mayandro.cleanarchbaseproject.data.model.apimodel

import com.google.gson.annotations.SerializedName


data class PokemonTypesResponse (
    @SerializedName("results") val results : List<PokemonTypesResponseItem>
)

data class PokemonTypesResponseItem (
    @SerializedName("name") val name : String
)

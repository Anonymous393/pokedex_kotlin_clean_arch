package com.mayandro.cleanarchbaseproject.data.model.apimodel

import com.google.gson.annotations.SerializedName


data class PokemonItemResponse (
    @SerializedName("results") val results : List<PokemonItemResponseItem>
)

data class PokemonItemResponseItem (
    @SerializedName("name") val name : String
)

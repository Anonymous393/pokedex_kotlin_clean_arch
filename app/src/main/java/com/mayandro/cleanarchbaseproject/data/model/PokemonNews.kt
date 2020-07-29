package com.mayandro.cleanarchbaseproject.data.model

import com.google.gson.annotations.SerializedName

data class PokemonNews(
    @SerializedName("title")
    val title : String,

    @SerializedName("description")
    val description : String,

    @SerializedName("image")
    val image : Int
)
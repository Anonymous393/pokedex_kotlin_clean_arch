package com.mayandro.cleanarchbaseproject.data.model.apimodel

import com.google.gson.annotations.SerializedName

data class PokemonResponse (
    @SerializedName("abilities")
    val abilities : List<Abilities>?,

    @SerializedName("base_experience")
    val baseExperience : Int,

    @SerializedName("forms")
    val forms : List<Forms>?,

    @SerializedName("game_indices")
    val gameIndices : List<GameIndices>?,

    @SerializedName("height")
    val height : Int,

    @SerializedName("id")
    val id : Int,

    @SerializedName("is_default")
    val isDefault : Boolean,

    @SerializedName("location_area_encounters")
    val locationAreaEncounters : String,

    @SerializedName("moves")
    val moves : List<Moves>?,

    @SerializedName("name")
    val name : String,

    @SerializedName("order")
    val order : Int,

    @SerializedName("species")
    val species : Species?,

    @SerializedName("sprites")
    val sprites : Sprites?,

    @SerializedName("stats")
    val stats : List<Stats>?,

    @SerializedName("types")
    val types : List<Types>?,

    @SerializedName("weight")
    val weight : Int
)


data class Abilities (
    @SerializedName("ability") val ability : Ability,
    @SerializedName("is_hidden") val isHidden : Boolean,
    @SerializedName("slot") val slot : Int
)

data class Ability (
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String
)

data class Forms (
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String
)

data class GameIndices (
    @SerializedName("game_index") val game_index : Int,
    @SerializedName("version") val version : Version
)

data class Version (
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String
)

data class Moves (
    @SerializedName("move") val move : Move,
    @SerializedName("version_group_details") val versionGroupDetails : List<VersionGroupDetails>
)

data class Move (
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String
)

data class Species (
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String
)

data class Sprites (
    @SerializedName("back_default") val backDefault : String,
    @SerializedName("back_female") val backFemale : String,
    @SerializedName("back_shiny") val backShiny : String,
    @SerializedName("back_shiny_female") val backShinyFemale : String,
    @SerializedName("front_default") val frontDefault : String,
    @SerializedName("front_female") val frontFemale : String,
    @SerializedName("front_shiny") val frontShiny : String,
    @SerializedName("front_shiny_female") val frontShinyFemale : String
)

data class Stats (
    @SerializedName("base_stat") val baseStat : Int,
    @SerializedName("effort") val effort : Int,
    @SerializedName("stat") val stat : Stat
)

data class Stat (
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String
)

data class Types (
    @SerializedName("slot") val slot : Int,
    @SerializedName("type") val type : Type
)

data class Type (
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String
)

data class VersionGroupDetails (
    @SerializedName("level_learned_at") val levelLearnedAt : Int,
    @SerializedName("move_learn_method") val moveLearnMethod : MoveLearnMethod,
    @SerializedName("version_group") val versionGroup : VersionGroup
)

data class MoveLearnMethod (
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String
)

data class VersionGroup (
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String
)


package com.mayandro.cleanarchbaseproject.utility

import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.data.model.apimodel.PokedexItem
import com.mayandro.cleanarchbaseproject.data.model.apimodel.PokedexPokemon
import com.mayandro.cleanarchbaseproject.data.model.apimodel.PokemonResponse
import com.mayandro.cleanarchbaseproject.data.model.apimodel.PokemonResponseModel
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.Pokemon
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonData
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonMoves
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonStats

fun PokedexPokemon.toPokemon(): Pokemon {
    return Pokemon(
        orderID = this.id,
        number = this.num,
        name = this.name,
        types = this.type,
        starter = false,
        legendary = false,
        mythical = false,
        ultraBeast = false,
        mega = false,
        gen = 0,
        sprite = this.img
    )
}

fun List<PokedexPokemon>.toPokemonList(): List<Pokemon> {
    var list = mutableListOf<Pokemon>()
    this.forEach {
        list.add(it.toPokemon())
    }
    return list
}

fun PokedexItem.toPokemon(): Pokemon {
    return Pokemon(
        orderID = this.orderID,
        number = this.nDex,
        name = this.name,
        types = listOf(this.type1, this.type2),
        starter = this.note == "Starter",
        legendary = this.note == "Legendary",
        mythical = this.note == "Mythical",
        ultraBeast = this.note == "Ultra-Beast",
        mega = this.name.contains("Mega"),
        gen = 0,
        sprite = this.image
    )
}

fun List<PokedexItem>.toPokemonsList(): List<Pokemon> {
    var list = mutableListOf<Pokemon>()
    this.forEach {
        list.add(it.toPokemon())
    }
    return list
}


fun PokedexItem.toPokemonStats(): PokemonStats {
    return PokemonStats(
        orderID = this.orderID,
        number = this.nDex,
        hp = this.hp,
        specialAttack = this.spatk,
        specialDefense = this.spdef,
        speed = this.spe,
        defense = this.def,
        attack = this.atk
    )
}





fun PokemonResponseModel.toPokemon(id: Int): Pokemon {
    return Pokemon(
        orderID = id,
        number = this.number,
        name = this.name,
        types = this.types,
        starter = this.starter,
        legendary = this.legendary,
        mythical = this.mythical,
        ultraBeast = this.ultraBeast,
        mega = this.mega,
        gen = this.gen,
        sprite = this.sprite
    )
}

fun PokemonResponseModel.toPokemonData(id: Int): PokemonData {
    return PokemonData(
        orderID = id,
        number = this.number,
        species = this.species,
        gender = this.gender,
        height = this.height,
        weight = this.weight,
        description = this.description,
        eggGroups = this.eggGroups,
        family = this.family
    )
}

fun PokemonResponse.toPokemonMoves(): PokemonMoves {
    val normalAbility = mutableListOf<String>()
    val hiddenAbility = mutableListOf<String>()
    val movesList = mutableListOf<String>()
    this.abilities?.let { list ->
        list.forEach {
            if(it.isHidden) {
                hiddenAbility.add(it.ability.name)
            } else {
                normalAbility.add(it.ability.name)
            }
        }
    }

    this.moves?.let {
        it.forEach {
            movesList.add(it.move.name)
        }
    }

    return PokemonMoves(
        orderID = this.order,
        number = this.id,
        normal = normalAbility,
        hidden = hiddenAbility,
        moves = movesList
    )
}

fun String.getPokemonTypeResId(): Int {
    return when(this.toLowerCase()) {
        "bug" -> R.drawable.type_bug
        "dark" -> R.drawable.type_dark
        "dragon" -> R.drawable.type_dragon
        "electric" -> R.drawable.type_electric
        "fairy" -> R.drawable.type_fairy
        "fighting" -> R.drawable.type_fight
        "fire" -> R.drawable.type_fire
        "flying" -> R.drawable.type_flying
        "ghost" -> R.drawable.type_ghost
        "grass" -> R.drawable.type_grass
        "ground" -> R.drawable.type_ground
        "ice" -> R.drawable.type_ice
        "normal" -> R.drawable.type_normal
        "poison" -> R.drawable.type_poison
        "psychic" -> R.drawable.type_psychic
        "rock" -> R.drawable.type_rock
        "steel" -> R.drawable.type_steel
        "water" -> R.drawable.type_water
        else -> throw Exception()
    }
}

fun String.getPokemonTypeBackgroundResId(): Int {
    return when(this) {
        "Bug" -> R.drawable.details_type_bg_bug
        "Dark" -> R.drawable.details_type_bg_dark
        "Dragon" -> R.drawable.details_type_bg_dragon
        "Electric" -> R.drawable.details_type_bg_electric
        "Fairy" -> R.drawable.details_type_bg_fairy
        "Fighting" -> R.drawable.details_type_bg_fighting
        "Fire" -> R.drawable.details_type_bg_fire
        "Flying" -> R.drawable.details_type_bg_flying
        "Ghost" -> R.drawable.details_type_bg_ghost
        "Grass" -> R.drawable.details_type_bg_grass
        "Ground" -> R.drawable.details_type_bg_ground
        "Ice" -> R.drawable.details_type_bg_ice
        "Normal" -> R.drawable.details_type_bg_normal
        "Poison" -> R.drawable.details_type_bg_poison
        "Psychic" -> R.drawable.details_type_bg_psychic
        "Rock" -> R.drawable.details_type_bg_rock
        "Steel" -> R.drawable.details_type_bg_steel
        "Water" -> R.drawable.details_type_bg_water
        else -> throw Exception()
    }
}
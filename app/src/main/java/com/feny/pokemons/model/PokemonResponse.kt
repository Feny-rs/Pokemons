package com.feny.pokemons.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val id: Int,
    val name: String,
    val types: List<PokemonType>,
    val height: Int,  // Height in decimetres
    val weight: Int,  // Weight in hectograms (1 hectogram = 100 grams)
    val stats: List<Stats>, // List of stats
    val sprites: PokemonSprites // Sprites for different forms
)

data class PokemonType(
    val type: Type
)

data class Stats (
    @Expose
    @SerializedName("base_stat")
    val baseStat: Int,

    @Expose
    @SerializedName("stat")
    val stat: Stat,
)

data class PokemonSprites(
    val frontDefault: String?,
    val frontShiny: String?,
    // Add more sprite properties as needed
)

data class Type(
    val name: String
)

data class Stat(
    val name: String
)
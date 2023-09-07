package com.feny.pokemons.model

data class PokemonResponse(
    val id: Int,
    val name: String,
    val types: List<PokemonType>,
    // Add more properties as needed
)

data class PokemonType(
    val type: Type
)

data class Type(
    val name: String
)
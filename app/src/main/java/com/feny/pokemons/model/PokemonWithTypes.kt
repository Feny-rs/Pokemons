package com.feny.pokemons.model

data class PokemonWithTypes(
    val pokemon: PokemonListItem,
    val types: List<String>?
)

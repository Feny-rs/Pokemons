package com.feny.pokemons.model

data class PokemonListResponse(
    val results: List<PokemonListItem>
)

data class PokemonListItem(
    val name: String,
    val url: String
)
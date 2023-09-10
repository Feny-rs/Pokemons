package com.feny.pokemons.model

data class PokemonListResponse(
    val results: ArrayList<PokemonListItem> = arrayListOf()
)

data class PokemonListItem(
    val name: String,
    val url: String
)
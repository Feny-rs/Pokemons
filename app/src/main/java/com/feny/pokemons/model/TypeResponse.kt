package com.feny.pokemons.model

data class TypeResponse(
    val slot: Int,
    val type: Types
)

data class Types(
    val name: String,
    val url: String
)

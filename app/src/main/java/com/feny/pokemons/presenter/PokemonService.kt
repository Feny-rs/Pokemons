package com.feny.pokemons.presenter

import com.feny.pokemons.model.PokemonListResponse
import com.feny.pokemons.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<PokemonListResponse>

    @GET("pokemon/{id}")
    fun getPokemonDetails(@Path("id") id: Int): Call<PokemonResponse>
}


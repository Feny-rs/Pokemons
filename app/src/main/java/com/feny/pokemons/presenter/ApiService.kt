package com.feny.pokemons.presenter

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private const val BASE_URL = "https://pokeapi.co/api/v2/" // Base URL of the PokeAPI

    fun create(): PokemonService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PokemonService::class.java)
    }
}

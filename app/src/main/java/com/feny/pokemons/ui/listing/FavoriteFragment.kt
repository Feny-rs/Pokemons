package com.feny.pokemons.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.feny.pokemons.databinding.FragmentFavoritePokemonsBinding
import com.feny.pokemons.model.PokemonListItem
import com.feny.pokemons.model.PokemonResponse
import com.feny.pokemons.presenter.PokemonService
import com.feny.pokemons.ui.adapter.PokemonAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FavoriteFragment : Fragment() {

    // Firebase
    private val userId = FirebaseAuth.getInstance().currentUser?.uid
    private val favoritesRef = FirebaseDatabase.getInstance().getReference("users/$userId/favorites")

    // RecyclerView and adapter
    private lateinit var binding: FragmentFavoritePokemonsBinding
    private lateinit var adapter: PokemonAdapter
    private lateinit var service: PokemonService
    // List to store favorite Pokemon data
    private val favoritePokemonList: MutableList<PokemonListItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritePokemonsBinding.inflate(inflater, container, false)
        val view = binding.root

        adapter = PokemonAdapter() // Initialize your FavoriteAdapter

        binding.rvPokemon.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPokemon.adapter = adapter

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(PokemonService::class.java)
        retrieveFavoritePokemon()

        return view
    }

    private fun retrieveFavoritePokemon() {
        if (userId != null) {
            favoritesRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val favoritePokemonIds = snapshot.children.mapNotNull { it.key?.toIntOrNull() }

                    // Clear the existing list when new data is retrieved
                    favoritePokemonList.clear()

                    // Now you have the list of favorite Pokémon IDs for the current user
                    // You can use this list to fetch and store the corresponding Pokemon data
                    fetchPokemonDetails(favoritePokemonIds)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database read error
                }
            })
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun fetchPokemonDetails(pokemonIds: List<Int>) {
        val pokemonList: MutableList<PokemonListItem> = mutableListOf()

        val calls = mutableListOf<Call<PokemonResponse>>()
        // Create calls for each Pokemon ID and add them to the list
        for (pokemonId in pokemonIds) {
            val call = service.getPokemonDetails(pokemonId)
            calls.add(call)
        }

        // Execute all calls concurrently using Coroutine's async
        GlobalScope.launch(Dispatchers.IO) {
            val deferredResults = calls.map { async { it.execute() } }

            for (deferredResult in deferredResults) {
                try {
                    val response = deferredResult.await()
                    if (response.isSuccessful) {
                        val pokemon = response.body()
                        if (pokemon != null) {
                            val listItem = PokemonListItem(
                                name = pokemon.name,
                                url = "https://pokeapi.co/api/v2/pokemon/${pokemon.id}/"
                            )
                            pokemonList.add(listItem)
                        }
                    }
                } catch (e: Exception) {
                    // Handle any exceptions or errors
                    e.printStackTrace()
                }
            }
            activity?.runOnUiThread {
                adapter.replaceData(pokemonList)
            }
        }
    }

}
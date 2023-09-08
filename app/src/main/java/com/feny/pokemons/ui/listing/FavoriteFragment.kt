package com.feny.pokemons.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.feny.pokemons.databinding.FragmentFavoritePokemonsBinding
import com.feny.pokemons.ui.adapter.PokemonAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FavoriteFragment : Fragment() {

    // Firebase
    private val userId = FirebaseAuth.getInstance().currentUser?.uid
    private val favoritesRef = FirebaseDatabase.getInstance().getReference("users/$userId/favorites")

    // RecyclerView and adapter
    private lateinit var binding: FragmentFavoritePokemonsBinding
    private lateinit var adapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritePokemonsBinding.inflate(inflater, container, false)
        val view = binding.root

        adapter = PokemonAdapter()

        // Set the adapter for the RecyclerView
        binding.rvPokemon.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPokemon.adapter = adapter

        // Retrieve and display the list of favorite Pokémon
        retrieveFavoritePokemon()

        return view
    }

    private fun retrieveFavoritePokemon() {
        if (userId != null) {
            favoritesRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val favoritePokemonIds = snapshot.children.mapNotNull { it.key?.toIntOrNull() }

                    // Now you have the list of favorite Pokémon IDs for the current user
                    // You can use this list to filter and display favorite Pokémon in the adapter
                    adapter.setFavoritePokemonIds(favoritePokemonIds)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database read error
                }
            })
        }
    }
}
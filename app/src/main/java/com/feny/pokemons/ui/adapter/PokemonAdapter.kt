package com.feny.pokemons.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feny.pokemons.databinding.PokemonItemBinding
import com.feny.pokemons.databinding.ProgressBarBinding
import com.feny.pokemons.model.PokemonListItem
import com.feny.pokemons.model.PokemonResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.ArrayList

class PokemonAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Firebase
    private val userId = FirebaseAuth.getInstance().currentUser?.uid
    private val favoritesRef = FirebaseDatabase.getInstance().getReference("users/$userId/favorites")

    private var pokemonList: List<PokemonListItem> = emptyList()
    private var filteredPokemonList: List<PokemonListItem> = emptyList()
    private var currentFilterQuery: String = ""
    private val favoriteStateMap = mutableMapOf<Int, Boolean>()

    private val VIEW_TYPE_POKEMON = 0
    private val VIEW_TYPE_PROGRESS_BAR = 1

    private var isLoading = false
    private var isFav = false

    fun setLoading(loading: Boolean) {
        isLoading = loading
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(pokemonNumber: Int)
        fun onAddFavorite(pokemonNumber: Int)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    inner class PokemonViewHolder(private val binding: PokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            // Set an item click listener for the adapter
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val parts = filteredPokemonList[position].url.split("/")
                    val pokemonNumber = parts[parts.size - 2].toInt()
                    // Call the item click listener with the Pokemon number
                    itemClickListener?.onItemClick(pokemonNumber)
                }
            }
        }
        @SuppressLint("SetTextI18n")
        fun bind(pokemon: PokemonListItem) {
            binding.pokemonName.text = pokemon.name
            val parts = pokemon.url.split("/")
            val pokemonNumber = parts[parts.size - 2].toInt()
            binding.pokemonNumber.text = "#00$pokemonNumber"

            // Load Pokemon image using Glide
            val imageUrl =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${pokemonNumber}.png"
            Glide.with(binding.root)
                .load(imageUrl)
                .into(binding.pokemonImage)

            binding.favoriteButton.isChecked = isFav
            binding.favoriteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val parts = filteredPokemonList[position].url.split("/")
                    val pokemonNumber = parts[parts.size - 2].toInt()
                    val pokemonDetails = PokemonListItem(
                        name = filteredPokemonList[position].name,
                        url = filteredPokemonList[position].url
                    )
                    toggleFavorite(pokemonNumber, pokemonDetails)
                }
            }
        }
    }

    inner class ProgressBarViewHolder(private val binding: ProgressBarBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_POKEMON -> {
                val binding = PokemonItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                PokemonViewHolder(binding)
            }
            VIEW_TYPE_PROGRESS_BAR -> {
                val progressBarBinding = ProgressBarBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ProgressBarViewHolder(progressBarBinding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_POKEMON -> {
                val pokemonViewHolder = holder as PokemonViewHolder
                pokemonViewHolder.bind(filteredPokemonList[position])
            }
            VIEW_TYPE_PROGRESS_BAR -> {
                // Handle binding the progress bar item if needed
            }
        }
    }

    override fun getItemCount(): Int {
        // Add 1 for the progress bar if isLoading is true
        return if (isLoading) {
            filteredPokemonList.size + 1
        } else {
            filteredPokemonList.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        // Return the view type for the progress bar when it's the last item
        return if (position == filteredPokemonList.size && isLoading) {
            VIEW_TYPE_PROGRESS_BAR
        } else {
            VIEW_TYPE_POKEMON
        }
    }

    fun addData(newPokemonList: List<PokemonListItem>) {
        // Append new data to the existing list
        pokemonList = pokemonList + newPokemonList
        // Apply the current filter to the updated list
        filter(currentFilterQuery)
    }

    fun replaceData(newPokemonList: List<PokemonListItem>) {
        // Append new data to the existing list
        pokemonList = newPokemonList
        // Apply the current filter to the updated list
        filter(currentFilterQuery)
    }

    fun filter(query: String) {
        currentFilterQuery = query
        val lowerCaseQuery = query.toLowerCase(Locale.getDefault())
        filteredPokemonList = if (query.isEmpty()) {
            pokemonList
        } else {
            pokemonList.filter { pokemon ->
                pokemon.name.toLowerCase(Locale.getDefault()).contains(lowerCaseQuery)
            }
        }
        notifyDataSetChanged()
    }

    fun getFavoriteState(pokemonNumber: Int): Boolean {
        return favoriteStateMap[pokemonNumber] ?: false
    }

    fun setFavoriteState(favorites: List<PokemonResponse>, isFavorite: Boolean) {
        favoriteStateMap[favorites[0].id] = isFavorite
        notifyDataSetChanged()
    }

    fun toggleFavorite(pokemonNumber: Int, pokemonDetails: PokemonListItem) {
        val isFavorite = isFav

        if (isFavorite) {
            // Remove from favorites
            favoritesRef.child(pokemonNumber.toString()).removeValue()
            isFav = false
        } else {
            // Add to favorites
            favoritesRef.child(pokemonNumber.toString()).setValue(pokemonDetails)
            isFav = true
        }
    }
}
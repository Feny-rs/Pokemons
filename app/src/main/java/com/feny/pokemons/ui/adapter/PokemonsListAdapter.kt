package com.feny.pokemons.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.feny.pokemons.databinding.PokemonItemBinding
import com.feny.pokemons.model.Pokemon
import com.squareup.picasso.Picasso

class PokemonsListAdapter(
    private val clickListener: (Pokemon) -> Unit,
    private val favoriteButtonClickListener: (Pokemon, Boolean) -> Unit,
    private val isPokemonFavorite: (Int) -> Boolean,
    private val lastPosition: Int?) : ListAdapter<Pokemon, PokemonsListAdapter.PokemonViewHolder>(REPO_COMPARATOR){

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem
        }
    }

    // ViewHolder class to hold the views for each item
    class PokemonViewHolder(private val binding: PokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) {
            binding.pokemonNumber.text = "#"+pokemon.id.toString()
            binding.pokemonName.text = pokemon.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PokemonItemBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon)

        // Set click listeners and other logic here if needed
    }
}
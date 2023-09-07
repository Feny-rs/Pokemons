package com.feny.pokemons.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feny.pokemons.databinding.ItemSkillsBinding
import com.feny.pokemons.model.Stats

class PokemonStatsAdapter(private val statsList: List<Stats>) :
    RecyclerView.Adapter<PokemonStatsAdapter.PokemonStatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonStatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSkillsBinding.inflate(inflater, parent, false)
        return PokemonStatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonStatViewHolder, position: Int) {
        val stat = statsList[position]
        holder.bind(stat)
    }

    override fun getItemCount(): Int {
        return statsList.size
    }

    inner class PokemonStatViewHolder(private val binding: ItemSkillsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(stat: Stats) {
            binding.skills.text = stat.stat.name
            binding.skillsNumber.text = stat.baseStat.toString()
        }
    }
}
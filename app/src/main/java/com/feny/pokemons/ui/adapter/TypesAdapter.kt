package com.feny.pokemons.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feny.pokemons.R
import com.feny.pokemons.model.PokemonType

class TypesAdapter(private val typesList: List<PokemonType>) :
    RecyclerView.Adapter<TypesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameType: TextView = itemView.findViewById(R.id.nameType)
        val icon1: ImageView = itemView.findViewById(R.id.icon1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_types, parent, false) // Replace with your layout
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val typeItem = typesList[position]

        // Bind data to views
        holder.nameType.text = typeItem.type.name

        // Load image using Glide (replace with your image URL logic)
        when (typeItem.type.name) {
            "fire" -> holder.icon1.setImageResource(R.drawable.fire)
            "bug" -> holder.icon1.setImageResource(R.drawable.bug)
            "fairy" -> holder.icon1.setImageResource(R.drawable.fairy)
            "electric" -> holder.icon1.setImageResource(R.drawable.electric)
            "water" -> holder.icon1.setImageResource(R.drawable.water)
            "grass" -> holder.icon1.setImageResource(R.drawable.grass)
            "ice" -> holder.icon1.setImageResource(R.drawable.ice)
            "normal" -> holder.icon1.setImageResource(R.drawable.normal)
            "poison" -> holder.icon1.setImageResource(R.drawable.poison)
            "rock" -> holder.icon1.setImageResource(R.drawable.rock)
            "dragon" -> holder.icon1.setImageResource(R.drawable.dragon)
            "psychic" -> holder.icon1.setImageResource(R.drawable.psychic)
            "ground" -> holder.icon1.setImageResource(R.drawable.ground)
            "dark" -> holder.icon1.setImageResource(R.drawable.dark)
            "fighting" -> holder.icon1.setImageResource(R.drawable.fighting)
            "flying" -> holder.icon1.setImageResource(R.drawable.flying)
            "ghost" -> holder.icon1.setImageResource(R.drawable.ghost)
        }
    }

    override fun getItemCount(): Int {
        return typesList.size
    }
}
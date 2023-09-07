package com.feny.pokemons.ui.listing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.feny.pokemons.databinding.FragmentListingPokemonsBinding
import com.feny.pokemons.model.Pokemon

class ListingPokemonsView : Fragment() {

    private var binding: FragmentListingPokemonsBinding? = null
    private lateinit var viewBinding: FragmentListingPokemonsBinding
    private lateinit var currentType: String
    var pokemonsDisplayed: Int = 0
    var failureFlag = false
    var allTypesList: MutableList<Pokemon> = mutableListOf()
    var toLoadList: MutableList<Pokemon> = mutableListOf()
    var pagingFlag: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonsDisplayed = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
package com.feny.pokemons.ui.listing

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.feny.pokemons.databinding.FragmentPokemonDetailBinding
import com.feny.pokemons.model.PokemonResponse
import com.feny.pokemons.presenter.PokemonService
import com.feny.pokemons.ui.adapter.PokemonStatsAdapter
import com.feny.pokemons.ui.adapter.TypesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailPokemonFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: DetailPokemonFragmentArgs by navArgs()
        val pokemonNumber = args.pokemonNumber

        // Make a network request to fetch Pokemon details using Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PokemonService::class.java)
        val call = service.getPokemonDetails(pokemonNumber)

        call.enqueue(object : Callback<PokemonResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if (response.isSuccessful) {
                    val pokemonResponse = response.body()

                    // Populate the views with data
                    if (pokemonResponse != null) {
                        binding.pokemonsId.text = "#00${pokemonResponse.id} - ${pokemonResponse.name}"

                        Glide.with(requireContext())
                            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${pokemonNumber}.png")
                            .into(binding.ivImage)

                        binding.shine.setOnCheckedChangeListener { compoundButton, ativo ->

                            if (ativo) {
                                Glide.with(this@DetailPokemonFragment)
                                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/${pokemonNumber}.png")
                                    .into(binding.ivImage)

                            } else {
                                Glide.with(this@DetailPokemonFragment)
                                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${pokemonNumber}.png")
                                    .into(binding.ivImage)
                            }
                        }

                        val adapterTypes = TypesAdapter(pokemonResponse.types)
                        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                        binding.typesLinearLayout.layoutManager = layoutManager
                        binding.typesLinearLayout.adapter = adapterTypes

                        // Create an adapter for the RecyclerView and set it here
                        val adapter = PokemonStatsAdapter(pokemonResponse.stats)
                        binding.rvlPokeInfo.layoutManager = LinearLayoutManager(requireContext())
                        binding.rvlPokeInfo.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                // Handle network request failure
            }
        })

        // Handle the back button click
        binding.imgBack.setOnClickListener {
            // Navigate back to the previous fragment
            findNavController().navigateUp()
        }
    }
}
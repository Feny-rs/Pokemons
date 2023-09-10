package com.feny.pokemons.ui.listing

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feny.pokemons.databinding.FragmentPokemonBinding
import com.feny.pokemons.model.PokemonListItem
import com.feny.pokemons.model.PokemonListResponse
import com.feny.pokemons.presenter.PokemonService
import com.feny.pokemons.ui.adapter.PokemonAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonFragment : Fragment() {

    private lateinit var binding: FragmentPokemonBinding
    private lateinit var adapter: PokemonAdapter
    private lateinit var service: PokemonService
    private var listPokemon : ArrayList<PokemonListItem> = arrayListOf()
    private var currentPage = 0
    private val pageSize = 20
    private var isLoading = false
    private var isLastPage = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView and adapter
        adapter = PokemonAdapter()
        binding.rvPokemon.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPokemon.adapter = adapter

        adapter.setOnItemClickListener(object : PokemonAdapter.OnItemClickListener {
            override fun onItemClick(pokemonNumber: Int) {
                // Handle item click here, you have the Pokemon number
                val action = PokemonFragmentDirections.actionListFragmentToDetailPokemonFragment(
                    pokemonNumber
                )
                findNavController().navigate(action)
            }
        })

        val editText = binding.searchInput.keyword
        val endDrawable = editText.compoundDrawablesRelative[2]

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                // Filter the Pokemon list when the text changes
                val query = s.toString().trim()
                adapter.filter(query)
            }
        })
        endDrawable?.let { drawable ->
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            editText.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP &&
                    event.rawX >= (editText.right - drawable.bounds.width())
                ) {
                    editText.text.clear()
                    return@setOnTouchListener true
                }
                false
            }
        }

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PokemonService::class.java)

        // Fetch the initial page of data
        fetchNextPage()

        binding.rvPokemon.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                // Check if it's time to load the next page
                if (!isLoading && !isLastPage && totalItemCount <= (lastVisibleItem + 5)) {
                    isLoading = true
                    fetchNextPage()
                }
            }
        })
    }

    // Function to fetch the next page of data
    private fun fetchNextPage() {
        val call = service.getPokemonList(currentPage, pageSize)

        call.enqueue(object : Callback<PokemonListResponse> {
            override fun onResponse(
                call: Call<PokemonListResponse>,
                response: Response<PokemonListResponse>
            ) {
                if (response.isSuccessful) {
                    val newPokemonList = response.body()?.results ?: emptyList()
                    if (newPokemonList.isNotEmpty()) {
                        adapter.addData(newPokemonList)
                        currentPage++
                    } else {
                        isLastPage = true
                    }
                }
                isLoading = false
            }

            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                // Handle network request failure
                isLoading = false
            }
        })
    }
}
package com.feny.pokemons.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.feny.pokemons.R
import com.feny.pokemons.databinding.ActivityMainFeaturesViewBinding
import com.feny.pokemons.ui.listing.FavoriteFragment
import com.feny.pokemons.ui.listing.PokemonFragment
import com.feny.pokemons.ui.listing.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFeaturesView : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainFeaturesViewBinding

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.listFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_favorite -> {
                    navController.navigate(R.id.favoriteFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    navController.navigate(R.id.profileFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainFeaturesViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val navView: BottomNavigationView = binding.bottomNav
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // Load the initial fragment when the activity is created (if needed)
        // The Navigation component will handle the initial destination defined in your graph.
    }

    override fun onBackPressed() {
        // Use the NavController to handle the back button
        if (!navController.popBackStack()) {
            super.onBackPressed()
        }
    }
}
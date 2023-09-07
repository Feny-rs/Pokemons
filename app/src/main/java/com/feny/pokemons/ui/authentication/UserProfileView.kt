package com.feny.pokemons.ui.authentication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.feny.pokemons.databinding.FragmentProfilePokemonsBinding

class UserProfileView : AppCompatActivity() {

    private lateinit var binding: FragmentProfilePokemonsBinding
    private lateinit var session: SessionAuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfilePokemonsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        session = SessionAuthManager(this)

        binding.userName1.text
        binding.userName2.text

        binding.btnLogout.setOnClickListener {
            session.signOutOnSessionExpiration()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Close the splash activity so it's not accessible via the back button
        }
    }
}
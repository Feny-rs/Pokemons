/*
 * *
 *  * Created by fenyrahmasari on 05/09/2023, 13:52
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 05/09/2023, 13:51
 *
 */

package com.feny.pokemons.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.feny.pokemons.databinding.ActivityLoginBinding
import com.feny.pokemons.ui.MainFeaturesView
import com.feny.pokemons.ui.listing.ListingPokemonsView
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var session: SessionAuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the activity to full-screen mode
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        // Hide the action bar if present
        supportActionBar?.hide()

        // Use WindowInsetsController for newer Android versions
        val controller = ViewCompat.getWindowInsetsController(window.decorView)
        controller?.hide(WindowInsetsCompat.Type.statusBars())
        controller?.hide(WindowInsetsCompat.Type.navigationBars())

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialising auth object
        auth = FirebaseAuth.getInstance()
        session = SessionAuthManager(this)

        initView()
    }

    private fun initView() {
        binding.btnLogin.setOnClickListener {
            val email = binding.username.text.toString()
            val password = binding.password.text.toString()
            login(email, password)
        }

        binding.lineSignUp.setOnClickListener {
            val intent = Intent(this, RegistActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Success login", Toast.LENGTH_SHORT).show()
                    // Set the session expiration time to 5 minutes
                    session.setSessionExpirationTime(5 * 60 * 1000) // 5 minutes in milliseconds
                    val intent = Intent(this, MainFeaturesView::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val msg = it.exception.toString().substringAfter(": ")
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                    Log.d("Tag", "msg : " + it.exception.toString())
                }
            }
        } else {
            Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onStart() {
        super.onStart()

        if(auth.currentUser != null){
            val intent = Intent(this, MainFeaturesView::class.java)
            startActivity(intent)
        }
    }
}
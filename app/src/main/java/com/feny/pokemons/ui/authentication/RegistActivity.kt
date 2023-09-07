/*
 * *
 *  * Created by fenyrahmasari on 05/09/2023, 13:52
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 05/09/2023, 13:52
 *
 */

package com.feny.pokemons.ui.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.feny.pokemons.databinding.ActivityRegistBinding
import com.google.firebase.auth.FirebaseAuth

class RegistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistBinding
    private lateinit var auth: FirebaseAuth

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
        binding = ActivityRegistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        initView()
    }

    private fun initView() {
        binding.lineSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnRegist.setOnClickListener {
            val email = binding.username.text.toString()
            val pass = binding.password.text.toString()
            val confirmPass = binding.confirmPassword.text.toString()
            getRegistAuth(email, pass, confirmPass)
        }
    }

    private fun getRegistAuth(email: String, password: String, confirm: String) {
        if (email.isNotEmpty() && password.isNotEmpty() && confirm.isNotEmpty()) {
            if (password == confirm) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Success make an account", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val msg = it.exception.toString().substringAfter(": ")
                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                        Log.d("Tag", "msg : " + it.exception.toString())
                    }
                }
            } else {
                Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

        }
    }
}
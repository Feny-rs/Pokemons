package com.feny.pokemons.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.feny.pokemons.R
import com.feny.pokemons.ui.authentication.LoginActivity

class MainActivity : AppCompatActivity() {
    // Delay in milliseconds for how long the splash screen should be displayed
    private val splashDelay: Long = 3000 // 2 seconds

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
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            // Create an Intent to start your main activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Close the splash activity so it's not accessible via the back button
        }, splashDelay)
    }
}
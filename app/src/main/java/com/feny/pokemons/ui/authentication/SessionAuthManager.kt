package com.feny.pokemons.ui.authentication

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth

class SessionAuthManager(private val context: Context) {

    companion object {
        private const val SESSION_PREFS_NAME = "SessionPrefs"
        private const val KEY_EXPIRATION_TIME = "ExpirationTime"
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        SESSION_PREFS_NAME, Context.MODE_PRIVATE)
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    // Set the session expiration time (e.g., 30 minutes)
    fun setSessionExpirationTime(durationMillis: Long) {
        val expirationTime = System.currentTimeMillis() + durationMillis
        sharedPreferences.edit().putLong(KEY_EXPIRATION_TIME, expirationTime).apply()
    }

    // Check if the session has expired
    fun isSessionExpired(): Boolean {
        val expirationTime = sharedPreferences.getLong(KEY_EXPIRATION_TIME, 0)
        return System.currentTimeMillis() >= expirationTime
    }

    // Sign out the user when the session expires
    fun signOutOnSessionExpiration() {
        if (isSessionExpired()) {
            firebaseAuth.signOut()
            clearSession()
        }
    }

    // Clear session data
    private fun clearSession() {
        sharedPreferences.edit().remove(KEY_EXPIRATION_TIME).apply()
    }
}
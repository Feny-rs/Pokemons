package com.feny.pokemons.ui.authentication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.feny.pokemons.databinding.FragmentProfilePokemonsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.MetadataChanges

class UserProfileView : AppCompatActivity() {

    private lateinit var binding: FragmentProfilePokemonsBinding
    private lateinit var session: SessionAuthManager
    private val userId = FirebaseAuth.getInstance().currentUser!!.uid
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfilePokemonsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        session = SessionAuthManager(this)

        binding.userName1.text
        binding.userName2.text

        binding.btnLogout.setOnClickListener {
            session.signOutOnSessionExpiration()
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Close the splash activity so it's not accessible via the back button
        }
    }

//    override fun onStart() {
//        super.onStart()
//
//        val documentRef = db.collection("Usuários").document(usuarioId)
//
//        documentRef.addSnapshotListener(MetadataChanges.INCLUDE) { snapshot, e ->
//            if (snapshot != null) {
//                binding.userName1.text = snapshot.getString("email")
//            }
//
//        }
//    }
}
package com.example.islam360

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get FirebaseAuth instance
        val mAuth = FirebaseAuth.getInstance()

        // Check if a user is logged in
        val currentUser = mAuth.currentUser

        // Redirect based on authentication status after 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            if (currentUser != null) {
                // User is logged in, redirect to HomeActivity (or a similar activity)
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                // No user logged in, redirect to LoginActivity
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
            finish() // Ensure MainActivity does not remain in the back stack
        }, 3000)
    }
}

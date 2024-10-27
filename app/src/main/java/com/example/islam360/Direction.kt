package com.example.islam360

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.islam360.databinding.ActivityDirectionBinding

class Direction : AppCompatActivity() {

    private lateinit var binding: ActivityDirectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      //  binding = ActivityDirectionBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_direction)

        // Set up listeners for Skip and Allow buttons
        binding.skipButton.setOnClickListener {
            // Redirect to Home screen when Skip is clicked
            navigateToHome()
        }

        binding.allowButton.setOnClickListener {
            // Redirect to Home screen when Allow is clicked
            navigateToHome()
        }
    }

    // Function to handle navigation to HomeActivity
    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()  // Optional: Finish Direction activity so it can't be returned to
    }
}

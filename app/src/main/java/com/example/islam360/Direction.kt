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


    }

    // Function to handle navigation to HomeActivity
    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)

    }
}
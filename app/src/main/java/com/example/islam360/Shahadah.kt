package com.example.islam360

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.islam360.databinding.ActivityShahadahBinding

class Shahadah : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShahadahBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
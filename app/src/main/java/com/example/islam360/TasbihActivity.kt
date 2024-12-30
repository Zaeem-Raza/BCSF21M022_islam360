package com.example.islam360

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.islam360.databinding.ActivityTasbihBinding

class TasbihActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTasbihBinding

    private var count = 0
    private var currentPhraseIndex = 0
    private val tasbihPhrases = listOf("الله أكبر", "سبحان الله", "الحمد لله")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTasbihBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Tasbih phrase
        binding.allahuAkbarText.text = tasbihPhrases[currentPhraseIndex]

        // Handle Plus Button Click
        binding.plusIcon.setOnClickListener {
            count++
            binding.tasbihCount.text = count.toString()
        }

        // Handle Reset Button Click
        binding.resetIcon.setOnClickListener {
            count = 0
            binding.tasbihCount.text = count.toString()
        }

        // Handle Changing Tasbih Phrase on Text Click
        binding.allahuAkbarText.setOnClickListener {
            currentPhraseIndex = (currentPhraseIndex + 1) % tasbihPhrases.size
            binding.allahuAkbarText.text = tasbihPhrases[currentPhraseIndex]
            Toast.makeText(
                this,
                "Changed to ${binding.allahuAkbarText.text}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

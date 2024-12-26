package com.example.islam360

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuranNext : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quran_next)

        setupViews()
    }

    private fun setupViews() {
        // Setup back button
        findViewById<ImageView>(R.id.backButton)?.setOnClickListener {
            finish()
        }

        // Setup tabs
        setupTabs()

        // Get the mode from intent
        val mode = intent.getStringExtra("mode") ?: "default"
        handleMode(mode)
    }

    private fun setupTabs() {
        findViewById<TextView>(R.id.bySurahTab)?.setOnClickListener {
            updateTabSelection(0)
        }

        findViewById<TextView>(R.id.byParahTab)?.setOnClickListener {
            updateTabSelection(1)
        }

        findViewById<TextView>(R.id.favoriteTab)?.setOnClickListener {
            updateTabSelection(2)
        }
    }

    private fun updateTabSelection(selectedTab: Int) {
        val tabs = listOf(
            findViewById<TextView>(R.id.bySurahTab),
            findViewById<TextView>(R.id.byParahTab),
            findViewById<TextView>(R.id.favoriteTab)
        )

        tabs.forEach { tab ->
            tab?.setTextColor(Color.parseColor("#BBBBBB"))
        }
        tabs[selectedTab]?.setTextColor(Color.WHITE)
    }

    private fun handleMode(mode: String) {
        when (mode) {
            "fifteen_line" -> {
                // Handle 15 line Quran mode
                findViewById<TextView>(R.id.headerTitle)?.text = "15 Line Quran"
            }
            "fahm" -> {
                // Handle Fahm ul Quran mode
                findViewById<TextView>(R.id.headerTitle)?.text = "Fahm ul Quran"
            }
            "recitation" -> {
                // Handle Recitation mode
                findViewById<TextView>(R.id.headerTitle)?.text = "Recitation"
            }
            "understand" -> {
                // Handle Understand Quran mode
                findViewById<TextView>(R.id.headerTitle)?.text = "Understand Quran"
            }
            else -> {
                // Default Quran mode
                findViewById<TextView>(R.id.headerTitle)?.text = "Al-Quran"
            }
        }
    }
}
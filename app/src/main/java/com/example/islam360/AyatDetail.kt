package com.example.islam360

import MiniPlayerFragment
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AyatDetail : AppCompatActivity() {

    private var surahId: Int = -1
    private var ayahId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ayat_card)

        val arabicTextView = findViewById<TextView>(R.id.arabicText)
        val translationTextView = findViewById<TextView>(R.id.translationText)
        val playButton = findViewById<ImageButton>(R.id.btnPlayAyah)
        val toggleButton = findViewById<Button>(R.id.btnToggleTranslation)

        val arabicText = intent.getStringExtra("ARABIC_TEXT") ?: "No Arabic text"
        val urduTranslation = intent.getStringExtra("URDU_TRANSLATION") ?: "No Urdu translation"
        val englishTranslation = intent.getStringExtra("ENGLISH_TRANSLATION") ?: "No English translation"
        surahId = intent.getIntExtra("SURAH_ID", -1)
        ayahId = intent.getIntExtra("AYAH_ID", -1)

        arabicTextView.text = arabicText
        translationTextView.text = urduTranslation

        toggleButton.setOnClickListener {
            if (translationTextView.text == urduTranslation) {
                translationTextView.text = englishTranslation
                toggleButton.text = "Switch to Urdu"
            } else {
                translationTextView.text = urduTranslation
                toggleButton.text = "Switch to English"
            }
        }

        playButton.setOnClickListener {
            if (surahId != -1 && ayahId != -1) {
                playAyahAudio(surahId, ayahId)
            } else {
                Toast.makeText(this, "Invalid Surah or Ayah ID", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun playAyahAudio(surahId: Int, ayahId: Int) {
        val miniPlayerFragment = MiniPlayerFragment()
        miniPlayerFragment.show(supportFragmentManager, "MiniPlayer")
        miniPlayerFragment.playAudio(surahId, ayahId)
    }
}

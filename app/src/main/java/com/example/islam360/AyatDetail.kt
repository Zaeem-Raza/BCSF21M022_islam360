package com.example.islam360

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AyatDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayat_detail)

        val arabicTextView = findViewById<TextView>(R.id.ayatText)
        val translationTextView = findViewById<TextView>(R.id.translationText)

        // Get the Ayat details from intent
        val arabicText = intent.getStringExtra("ARABIC_TEXT") ?: "No Arabic text"
        val urduTranslation = intent.getStringExtra("URDU_TRANSLATION") ?: "No Urdu translation"
        val englishTranslation = intent.getStringExtra("ENGLISH_TRANSLATION") ?: "No English translation"

        // Display Arabic text
        arabicTextView.text = arabicText

        // Show Urdu translation by default
        translationTextView.text = englishTranslation

        // Allow switching between Urdu and English translations
        translationTextView.setOnClickListener {
            if (translationTextView.text == urduTranslation) {
                translationTextView.text = englishTranslation
            } else {
                translationTextView.text = urduTranslation
            }
        }
    }
}

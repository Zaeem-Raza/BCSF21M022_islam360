package com.example.islam360

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.islam360.R

class QuranNext : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quran_next)

        // Set a static text message "Quran"
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Quran"
    }
}

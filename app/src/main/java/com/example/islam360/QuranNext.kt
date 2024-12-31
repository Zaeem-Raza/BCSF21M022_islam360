package com.example.islam360

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.Adapters.SurahAdapter
import com.example.islam360.dataAccess.DbHelper
import com.example.islam360.models.SurahModel

class QuranNext : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quran_next)

        recyclerView = findViewById(R.id.recyclerViewSurahs)
        dbHelper = DbHelper(this)

        // Fetch Surah details from the database
        val surahDetailsList = dbHelper.surahDetails

        // Convert SurahDetails to SurahModel for adapter compatibility
        val surahList = surahDetailsList.map { surahDetail ->
            SurahModel(
                surahDetail.surahNumber.toInt(), // Convert Surah number to Int
                surahDetail.surahNameEnglish, // Use the English name
                0 // If Ayah count is unavailable, set a default value like 0
            )
        }

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SurahAdapter(surahList) { surah ->
            // Handle item click
            Toast.makeText(this, "Clicked: ${surah.surahName}", Toast.LENGTH_SHORT).show()
        }
    }
}

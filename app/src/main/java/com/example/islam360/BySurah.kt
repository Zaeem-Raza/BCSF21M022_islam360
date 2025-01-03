package com.example.islam360

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.Adapters.SurahAdapter
import com.example.islam360.dataAccess.DbHelper
import com.example.islam360.models.SurahModel

class BySurah : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_by_surah)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewSurahs)
        dbHelper = DbHelper(this)

        // Fetch Surah details from the database
        val surahDetailsList = dbHelper.surahDetails

        // Map SurahDetails to SurahModel
        val surahList = surahDetailsList.map { surahDetail ->
            SurahModel(
                surahID = surahDetail.surahNumber.toInt(), // Convert to Int
                surahName = surahDetail.surahNameEnglish, // Use English Name
                ayahCount = dbHelper.getVerseCountForSurahs()[surahDetail.surahNumber.toInt()] ?: 0
            )
        }

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SurahAdapter(surahList) { surah ->
            // Handle click events (e.g., navigate to AyatActivity)
        }
    }
}

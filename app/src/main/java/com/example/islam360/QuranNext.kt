package com.example.islam360

import android.content.Intent
import android.os.Bundle
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

        // Fetch the verse counts
        val verseCounts = dbHelper.getVerseCountForSurahs()

        // Convert SurahDetails to SurahModel for adapter compatibility
        val surahList = surahDetailsList.map { surahDetail ->
            val ayahCount = verseCounts[surahDetail.surahNumber.toInt()] ?: 0 // Default to 0 if not found
            SurahModel(
                surahID = surahDetail.surahNumber.toInt(), // Convert Surah number to Int
                surahName = surahDetail.surahNameEnglish, // Use the English name
                ayahCount = ayahCount // Set the fetched verse count
            )
        }

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SurahAdapter(surahList) { surah ->
            // Handle item click
            val intent = Intent(this, AyatActivity::class.java)
            intent.putExtra("SURAH_ID", surah.surahID) // Pass Surah ID
            intent.putExtra("SURAH_NAME", surah.surahName) // Pass Surah Name
            startActivity(intent)
        }
    }
}

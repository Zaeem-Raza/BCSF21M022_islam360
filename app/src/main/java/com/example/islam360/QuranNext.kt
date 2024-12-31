package com.example.islam360

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.Adapters.SurahAdapter
import com.example.islam360.dataAccess.SQLiteHelper
import com.example.islam360.models.SurahModel

class QuranNext : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dbHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quran_next)

        recyclerView = findViewById(R.id.recyclerViewSurahs)
        dbHelper = SQLiteHelper(this)

        // Fetch Surah data from the database
        val surahList = dbHelper.getAllSurahs()

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SurahAdapter(surahList) { surah ->
            // Handle item click
            Toast.makeText(this, "Clicked: ${surah.surahName}", Toast.LENGTH_SHORT).show()
        }
    }
}

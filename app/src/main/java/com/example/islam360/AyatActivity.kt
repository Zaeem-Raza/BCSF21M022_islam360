package com.example.islam360

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.Adapters.AyatAdapter
import com.example.islam360.dataAccess.DbHelper

class AyatActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayat)

        recyclerView = findViewById(R.id.recyclerViewAyat)
        dbHelper = DbHelper(this)

        val surahId = intent.getIntExtra("SURAH_ID", -1)
        val surahName = intent.getStringExtra("SURAH_NAME") ?: "Unknown Surah"

        if (surahId != -1) {
            val ayatList = dbHelper.getSurah(surahId)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = AyatAdapter(ayatList, this)


            title = "Surah: $surahName" // Set the title to the Surah name
        } else {
            Toast.makeText(this, "Invalid Surah ID", Toast.LENGTH_SHORT).show()
        }
    }
}

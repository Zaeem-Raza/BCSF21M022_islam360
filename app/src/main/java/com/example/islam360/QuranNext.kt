package com.example.islam360

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.Adapters.SurahAdapter
import com.example.islam360.dataAccess.DbHelper
import com.example.islam360.models.SurahModel
import android.widget.EditText

class QuranNext : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dbHelper: DbHelper
    private lateinit var surahAdapter: SurahAdapter
    private lateinit var searchBar: EditText
    private var surahList = mutableListOf<SurahModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quran_next)

        recyclerView = findViewById(R.id.recyclerViewSurahs)
        searchBar = findViewById(R.id.searchBar)
        dbHelper = DbHelper(this)

        // Fetch Surah details from the database
        val surahDetailsList = dbHelper.surahDetails

        // Fetch the verse counts
        val verseCounts = dbHelper.getVerseCountForSurahs()

        // Convert SurahDetails to SurahModel for adapter compatibility
        surahList = surahDetailsList.map { surahDetail ->
            val ayahCount = verseCounts[surahDetail.surahNumber.toInt()] ?: 0 // Default to 0 if not found
            SurahModel(
                surahID = surahDetail.surahNumber.toInt(), // Convert Surah number to Int
                surahName = surahDetail.surahNameUrdu, // Use the urdu name
                ayahCount = ayahCount // Set the fetched verse count
            )
        }.toMutableList()

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        surahAdapter = SurahAdapter(surahList) { surah ->
            // Handle item click
            val intent = Intent(this, AyatActivity::class.java)
            intent.putExtra("SURAH_ID", surah.surahID) // Pass Surah ID
            intent.putExtra("SURAH_NAME", surah.surahName) // Pass Surah Name
            startActivity(intent)
        }
        recyclerView.adapter = surahAdapter

        // Add TextWatcher to search bar
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterSurahList(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterSurahList(query: String) {
        val filteredList = surahList.filter {
            it.surahName.contains(query, ignoreCase = true)
        }
        surahAdapter.updateList(filteredList)
    }
}

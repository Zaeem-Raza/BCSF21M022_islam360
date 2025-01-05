package com.example.islam360

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.Adapters.SurahAdapter
import com.example.islam360.dataAccess.DbHelper
import com.example.islam360.models.SurahModel
import android.widget.EditText
import com.example.islam360.dataAccess.LocalDbHelper
import com.google.firebase.auth.FirebaseAuth

class QuranNext : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dbHelper: DbHelper
    private lateinit var localDbHelper: LocalDbHelper
    private lateinit var surahAdapter: SurahAdapter
    private lateinit var searchBar: EditText
    private lateinit var favoritesButton: Button
    private var surahList = mutableListOf<SurahModel>()
    private var showingFavorites = false // Boolean flag to track current state

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quran_next)

        recyclerView = findViewById(R.id.recyclerViewSurahs)
        searchBar = findViewById(R.id.searchBar)
        favoritesButton = findViewById(R.id.favoritesButton)
        dbHelper = DbHelper(this)
        localDbHelper = LocalDbHelper(this)

        // Fetch Surah details from the database
        val surahDetailsList = dbHelper.surahDetails

        // Fetch the verse counts
        val verseCounts = dbHelper.getVerseCountForSurahs()

        // Convert SurahDetails to SurahModel for adapter compatibility
        surahList = surahDetailsList.map { surahDetail ->
            val ayahCount = verseCounts[surahDetail.surahNumber.toInt()] ?: 0 // Default to 0 if not found
            SurahModel(
                surahID = surahDetail.surahNumber.toInt(), // Convert Surah number to Int
                surahName = surahDetail.surahNameUrdu, // Use the Urdu name
                ayahCount = ayahCount // Set the fetched verse count
            )
        }.toMutableList()

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        surahAdapter = SurahAdapter(
            surahList = surahList,
            dbHelper = localDbHelper, // Pass the LocalDbHelper instance
            userId = FirebaseAuth.getInstance().currentUser?.uid ?: "", // Fetch the logged-in user's ID
            onSurahClick = { surah ->
                // Handle item click
                val intent = Intent(this, AyatActivity::class.java)
                intent.putExtra("SURAH_ID", surah.surahID) // Pass Surah ID
                intent.putExtra("SURAH_NAME", surah.surahName) // Pass Surah Name
                startActivity(intent)
            }
        )

        recyclerView.adapter = surahAdapter

        // Add TextWatcher to search bar
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterSurahList(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Set up Favorites Button
        favoritesButton.setOnClickListener {
            toggleFavorites()
        }
    }

    private fun filterSurahList(query: String) {
        val filteredList = surahList.filter {
            it.surahName.contains(query, ignoreCase = true)
        }
        surahAdapter.updateList(filteredList)
    }

    private fun toggleFavorites() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        if (showingFavorites) {
            // If showing favorites, switch back to showing all Surahs
            surahAdapter.updateList(surahList)
            showingFavorites = false
            favoritesButton.text = "Favourites" // Update button text
        } else {
            // If showing all Surahs, switch to showing only favorites
            val favoritesList = localDbHelper.getFavorites(userId)
            surahAdapter.updateList(favoritesList)
            showingFavorites = true
            favoritesButton.text = "All Surahs" // Update button text
        }
    }
}
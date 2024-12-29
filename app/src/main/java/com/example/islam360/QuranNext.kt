package com.example.islam360
import Surah
import SurahAdapter
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class QuranNext : AppCompatActivity() {

    private lateinit var surahAdapter: SurahAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quran_next)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        recyclerView.layoutManager = LinearLayoutManager(this)

        surahAdapter = SurahAdapter(emptyList()) { surah ->
            Toast.makeText(this, "Clicked: ${surah.name}", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = surahAdapter

        fetchSurahs()
    }

    private fun fetchSurahs() {
        progressBar.visibility = View.VISIBLE
        RetrofitInstance.api.getAllSurahs().enqueue(object : Callback<List<Surah>> {
            override fun onResponse(call: Call<List<Surah>>, response: Response<List<Surah>>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful && response.body() != null) {
                    surahAdapter.updateSurahs(response.body()!!)
                } else {
                    Toast.makeText(this@QuranNext, "Failed to load surahs", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Surah>>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@QuranNext, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
